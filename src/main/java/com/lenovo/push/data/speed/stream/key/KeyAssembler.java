package com.lenovo.push.data.speed.stream.key;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author gulei2
 *  input: dbtbl,logtime,pid,bizType,eventName,feedbackId,success,sid,errCode,packName,currVer,targetVer,value
 */
public class KeyAssembler {
	
	public static final String SEP = "\001";
	
	public static List<String> assemble(String feedbackEvent) {
		List<String> keyList = new ArrayList<String>();
		StringBuilder thekey = new StringBuilder();
		String[] parts = feedbackEvent.split(SEP);		
		//String thedate = parts[1].split(" ")[0];
		//if (thedate.length() != 8) {
		//	return null;
		//}
		
		if (parts[7].startsWith("rsys") && parts[5].startsWith("rinter2")) {
			thekey.append("pushmarketing"); // pushmarketing
			if (parts[6].equals("true")) {
				thekey.append("."); // pushmarketing.
				thekey.append("feedback"); // pushmarketing.feedback
				thekey.append("."); // pushmarketing.feedback.
				thekey.append(parts[5]); // pushmarketing.feedback.feedbackId
				thekey.append("."); // pushmarketing.feedback.feedbackId.
				thekey.append(parts[4]); // pushmarketing.feedback.feedbackId.eventName
				//thekey.append("."); // pushmarketing.feedback.feedbackId.eventName.
				//thekey.append(thedate); // pushmarketing.feedback.feedbackId.eventName.thedate
				keyList.add(thekey.toString());
			} else if (parts[6].equals("false")) {
				thekey.append("."); // pushmarketing.
				thekey.append("error"); // pushmarketing.error
				thekey.append("."); // pushmarketing.error.
				thekey.append(parts[5]); // pushmarketing.error.feedbackId
				thekey.append("."); // pushmarketing.error.feedbackId.
				thekey.append(parts[4]);  // pushmarketing.error.feedbackId.eventName
				//thekey.append("."); // pushmarketing.error.feedbackId.eventName.
				//thekey.append(parts[8]);  // pushmarketing.error.feedbackId.eventName.errCode
				//thekey.append("."); // pushmarketing.error.feedbackId.eventName.errCode.
				//thekey.append(thedate); // pushmarketing.error.feedbackId.eventName.errCode.thedate
				keyList.add(thekey.toString());
			}
			
			// Special treatment for camouflage
			if (parts[3].equals("fake")) {
				
				thekey.setLength(0);
				
				thekey.append("fake"); // fake
				if (parts[6].equals("true")) {
					thekey.append(".");  // fake.
					thekey.append("feedback"); // fake.feedback
					thekey.append("."); // fake.feedback.
					thekey.append(parts[5]); // fake.feedback.feedbackId
					thekey.append("."); // fake.feedback.feedbackId.
					thekey.append(parts[12]); // fake.feedback.feedbackId.packName
					thekey.append(".");  // fake.feedback.feedbackId.packName.
					thekey.append(parts[4]); // fake.feedback.feedbackId.packName.eventName
					//thekey.append("."); // fake.feedback.feedbackId.packName.eventName.
					//thekey.append(thedate); // fake.feedback.feedbackId.packName.eventName.thedate					
					keyList.add(thekey.toString());
				} else if (parts[6].equals("false")) {
					thekey.append("."); // fake.
					thekey.append("error"); // fake.error
					thekey.append("."); // fake.error.
					thekey.append(parts[5]); // fake.error.feedbackId
					thekey.append("."); // fake.error.feedbackId.
					thekey.append(parts[12]); // fake.error.feedbackId.packName
					thekey.append("."); // fake.error.feedbackId.packName.
					thekey.append(parts[4]); // fake.error.feedbackId.packName.eventName
					//thekey.append("."); // fake.error.feedbackId.packName.eventName.
					//thekey.append(parts[8]); // fake.error.feedbackId.packName.eventName.errCode
					//thekey.append("."); // fake.error.feedbackId.packName.eventName.errCode.
					//thekey.append(thedate); // fake.error.feedbackId.packName.eventName.errCode.thedate
					keyList.add(thekey.toString());
				} 
			}
			
		} else {
			
			if (!parts[7].equals("rwthr01") && parts[5].startsWith(parts[7])) {
				thekey.append("openplatform"); // openplatform
				if (parts[6].equals("true")) {
					thekey.append("."); // openplatform.
					thekey.append("feedback"); // openplatform.feedback
					thekey.append("."); // openplatform.feedback.
					//thekey.append(parts[7]); // openplatform.feedback.sid
					//thekey.append("."); // openplatform.feedback.sid.
					thekey.append(parts[5]); // openplatform.feedback.sid.feedbackId
					thekey.append("."); // openplatform.feedback.sid.feedbackId.
					thekey.append(parts[4]); // openplatform.feedback.sid.feedbackId.eventName
					//thekey.append("."); // openplatform.feedback.sid.feedbackId.eventName.
					//thekey.append(thedate);  // openplatform.feedback.sid.feedbackId.eventName.thedate
					keyList.add(thekey.toString());
				} else if (parts[6].equals("false")) {
					thekey.append(".");  // openplatform.
					thekey.append("error"); // openplatform.error
					thekey.append("."); // openplatform.error.
					//thekey.append(parts[7]); // openplatform.error.sid
					//thekey.append("."); // openplatform.feedback.sid.
					thekey.append(parts[5]); // openplatform.feedback.sid.feedbackId
					thekey.append(".");  // openplatform.feedback.sid.feedbackId.
					thekey.append(parts[4]); // openplatform.feedback.sid.feedbackId.eventName
					//thekey.append("."); // openplatform.feedback.sid.feedbackId.eventName.
					//thekey.append(parts[8]);  // openplatform.feedback.sid.feedbackId.eventName.errCode
					//thekey.append("."); // openplatform.feedback.sid.feedbackId.eventName.errCode.
					//thekey.append(thedate); // openplatform.feedback.sid.feedbackId.eventName.thedate
					keyList.add(thekey.toString());
				} 
			}			
		}
		
		return keyList;
	}
	
	public static void main(String[] args) {
		String testStr = "push.feedback20150505 14:23:43pid-1430807023142fakeeventName-143080702314210080-1430807023142truersyserrCode-1430807023142packName-1430807023142currVer-1430807023142targetVer-1430807023142value-1430807023142";
		List<String> thekeys = assemble(testStr);
		for (String thekey : thekeys) {
			System.out.println(thekey);
		}
		
	}

}
