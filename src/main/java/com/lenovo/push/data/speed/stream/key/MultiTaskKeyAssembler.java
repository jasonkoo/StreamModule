package com.lenovo.push.data.speed.stream.key;

public class MultiTaskKeyAssembler {
	
	public static final String SEP = "\001";
	public static final int NUM_FIELDS = 14;
	
	public static String assemble(String feedbackEvent) {
		String[] parts = feedbackEvent.split(SEP);
		int len = parts.length;
		
		if (len == NUM_FIELDS && !parts[len - 1].equals("")) {
			StringBuilder thekey = new StringBuilder();
			thekey.append("multitask"); // multitask
			thekey.append("."); // multitask.
			
			if (parts[6].equals("true")) {
				thekey.append("feedback"); // multitask.feedback
				thekey.append("."); // multitask.feedback.
				thekey.append(parts[5]); // multitask.feedback.feedbackId
				thekey.append("_");  // multitask.feedback.feedbackId_
				thekey.append(parts[len - 1]);  // multitask.feedback.feedbackId_stepId
				thekey.append(".");  // multitask.feedback.feedbackId_stepId.
				thekey.append(parts[4]);   // multitask.feedback.feedbackId_stepId.eventName				
			} else if (parts[6].equals("false")) {
				thekey.append("error");  // multitask.error
				thekey.append("."); // multitask.error.
				thekey.append(parts[5]); // multitask.error.feedbackId
				thekey.append("_");  // multitask.error.feedbackId_
				thekey.append(parts[len - 1]);  // multitask.error.feedbackId_stepId
				thekey.append(".");  // multitask.error.feedbackId_stepId.
				thekey.append(parts[4]);   // multitask.error.feedbackId_stepId.eventName				
			}
			return thekey.toString();
		} 
		
		return null;
	}
	
	public static void main(String[] args) {
		String test = "domestic.feedback20151020 00:00:00309219384pushrinter2_ff8080815059c52401507eb119a70069truersys1011";
		System.out.println(assemble(test));

	}

}
