package com.common.util;

public enum MessageProp {
	
	ERR_INS("ERR.INS"),
    
	ERR_DEL("ERR.DEL"),
	
    ERR_SAVE("ERR.SAVE"),
    
    INFO_INS("INFO.INS"),
    
    INFO_DEL("INFO.DEL"),
    
	INFO_SAVE("INFO.SAVE"),
	
	INFO_OK("INFO.OK");
	
    private String msg;
    
    private MessageProp(String p_msg) {
        msg = p_msg;
    }
    
    public String getMsg() {
        return msg;
    }
	
}
