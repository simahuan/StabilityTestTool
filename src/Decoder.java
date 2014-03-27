/* Copyright (c) Cisco Systems, Inc., 2013-2014. All rights reserved.
 *
 *	
 *  Revision:   1.4
 *  Date:   	Mar 14, 2014  
 *  Author:   	algodin
 *  
 *  Workfile:	Decoder.java
 *  
 *  Description: 
 *  	Abstract base class representing a generic decoder device
 *  
 */

import java.util.ArrayList;
import java.util.Date;


public abstract class Decoder extends SADevice {
	
	public ArrayList<String> dpmParam = new ArrayList<String>();
	
	Decoder(Date myday) 
	{
		super(myday);
	}
	
	boolean isDecoder() 	{ return true; }
	boolean isTranscoder() 	{ return false; }
	
	void configDpmParam(int maxChan, int dpmPerChan)
	{
		final String[] peMapAction = new String[] {"Drop", "Pass", "Map", "Xcode"};
		final String[] mapMode= new String[] {"Svc ID & PID", "Svc ID"};
		final String[] duplicMode = new String[] {"PSI Remap", "Pkt Copy"};
		final String[] unref = new String[] {"Drop", "Pass", "Model"};
		final String[] serviceId = new String[] {"Valid Ch", "All Ch"};
		final String[] psiRate = new String[] {"SA Std", "MPEG Min", "Auto"};
		final String[] psiOption = new String[] {"Pass All", "Drop All", "Ctl by Table"};
		final String[] gblCfgDPR = new String[] {"Drop", "Pass", "Regen"};
		final String[] gblCfgDPRP = new String[] {"Drop", "Pass", "Regen", "PwRC"};
		
		
		//---setOid("4.2.1.1.2.", 1, MAX_CHAN, channelNumber);
		addComment("*** Start DPM Section ***");
		
		if(dpmParam.get(4-1).toString().equalsIgnoreCase("Pass, Map & Drop")) {	 // mix case
			int peMapActParam[] = new int[] {1, 2, 3, 1, 2, 3, 1, 2};
			setOid("36.2.2.1.3.1.", 1, 8/*MAX_CHAN*/, peMapActParam);										// C4
			
		}
		else { // single mode case
			setOid("36.2.2.1.3.1.", 1, maxChan, indexOf(peMapAction, dpmParam.get(4-1).toString()));	// C4
		}
		
		final int dpmPemappingOpChannel[] = new  int[] {1, 2, 3, 4, 5, 6, 7, 8};
		setOid("36.2.2.1.5.1.", 1, maxChan, dpmPemappingOpChannel);
		
		// PMT PID 6000+shift
		setOidFunc1("36.2.2.1.4.1.", 1, maxChan);
	
		setOid("34.1.1.5.1", 2);		// RateControl {auto(1), user(2)}
		setOid("34.1.1.6.1", 68500000); // ASI UserRate 0..206000000 (default 68.5 Mb/sec)
		setOid("34.1.1.3.1", 7);		// Output Mode { noOutput(1), passThrough(2), serviceChannelOnly(3), mapPassthrough(4), mapserviceChannelOnly(5), fullDpmControl(6), transcoding(7) }
		setOid("34.1.1.4.1", 1); 		// Scrambling Mode { deScrambled(1), scrambled (2)}
		setOid("34.1.1.7.1", 2);		// Null Packet Insert { no(1), yes (2)}

		setOid("36.2.1.1.3.1",  indexOf(mapMode, dpmParam.get(8-1).toString()));		// C8
		setOid("36.2.1.1.4.1",  indexOf(duplicMode, dpmParam.get(9-1).toString())); 	// C9
		setOid("36.2.1.1.6.1",  indexOf(unref, dpmParam.get(10-1).toString()));		// C10
		setOid("36.2.1.1.8.1",  indexOf(serviceId, dpmParam.get(13-1).toString()));	// C13
		setOid("36.2.1.1.5.1",  indexOf(psiRate, dpmParam.get(12-1).toString()));	// C12
		setOid("36.2.1.1.7.1",  indexOf(psiOption, dpmParam.get(11-1).toString()));	// C11
		setOid("36.2.1.1.9.1",  indexOf(gblCfgDPR, dpmParam.get(14-1).toString()));	// C14
		setOid("36.2.1.1.10.1", indexOf(gblCfgDPR, dpmParam.get(15-1).toString()));	// C15
		setOid("36.2.1.1.11.1", indexOf(gblCfgDPR, dpmParam.get(16-1).toString()));	// C16
		setOid("36.2.1.1.12.1", indexOf(gblCfgDPR, dpmParam.get(17-1).toString()));	// C17
		setOid("36.2.1.1.13.1", indexOf(gblCfgDPR, dpmParam.get(18-1).toString()));	// C18
		setOid("36.2.1.1.14.1", indexOf(gblCfgDPRP, dpmParam.get(19-1).toString()));	// C19
		setOid("36.2.1.1.15.1", indexOf(gblCfgDPRP, dpmParam.get(20-1).toString()));	// C20
		setOid("36.2.1.1.16.1", indexOf(gblCfgDPRP, dpmParam.get(21-1).toString()));	// C21
		setOid("36.2.1.1.17.1", indexOf(gblCfgDPRP, dpmParam.get(22-1).toString()));	// C22
		setOid("36.2.1.1.18.1", indexOf(gblCfgDPR, dpmParam.get(23-1).toString()));	// C23
		setOid("36.2.1.1.19.1", indexOf(gblCfgDPR, dpmParam.get(24-1).toString()));	// C24
		setOid("36.2.1.1.21.1", indexOf(gblCfgDPR, dpmParam.get(25-1).toString()));	// C25
		setOid("36.2.1.1.22.1", indexOf(gblCfgDPR, dpmParam.get(26-1).toString()));	// C26
		setOid("36.2.1.1.23.1", indexOf(gblCfgDPR, dpmParam.get(27-1).toString()));	// C27
		setOid("36.2.1.1.24.1", indexOf(gblCfgDPR, dpmParam.get(28-1).toString()));	// C28
		setOid("36.2.1.1.25.1", indexOf(gblCfgDPR, dpmParam.get(29-1).toString()));	// C29
		setOid("36.2.1.1.26.1", indexOf(gblCfgDPR, dpmParam.get(30-1).toString()));	// C30
		setOid("36.2.1.1.27.1", indexOf(gblCfgDPR, dpmParam.get(31-1).toString()));	// C31
		setOid("36.2.1.1.28.1", indexOf(gblCfgDPR, dpmParam.get(32-1).toString()));	// C32
		
		// from: uic table dpm_pidmap
		int peGrpId[] = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
		setOidGroup1("36.2.3.1.3.", 1, maxChan, peGrpId, dpmPerChan);		// 15*8 PE
		
		int peStremCat[] = new int[] {2, 3, 4, 4, 4, 4, 5, 5, 6, 0xA, 8, 8, 8, 8, 8};
		setOidGroup2("36.2.3.1.6.", 1, maxChan, peStremCat, dpmPerChan);	// 15*8 Stream Category
		
		int peSteamInst[] = new int[] {1, 1, 1, 2, 3, 4, 1, 2, 1, 1, 1, 2, 3, 4, 5};
		setOidGroup2("36.2.3.1.7.", 1, maxChan, peSteamInst, dpmPerChan);	// 15*8 Stream Instance
		
		int peAction[] = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
		setOidGroup2("36.2.3.1.8.", 1, maxChan, peAction, dpmPerChan);		// 15*8 Action (Map/Drop)
		
		int pidPattern[] = new int[] { 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15 }; 
		int counter = 1;
		for(int i = 0; i < maxChan; i++)
		for(int j = 0; j < dpmPerChan; j++) {
			int pidNum = 6000 + pidPattern[j]*100 + i + 1;
			setOid("36.2.3.1.9."+counter, pidNum);
			counter++;
		} // j,i

		// always in use
		setOid("36.2.3.1.10.", 1, maxChan*dpmPerChan, 1);		// 15*8 PID in use {yes(1), no(2)}
		
		// always ASI
		setOid("36.2.3.1.2.", 1, maxChan*dpmPerChan, 1);			// 15*8 Output instance Id  {ASI(1), MPEGoIP(2)}
		
		addComment("*** End DPM Section ***");
	
	}
	

}
