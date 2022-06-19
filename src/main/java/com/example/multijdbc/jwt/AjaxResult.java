package com.example.multijdbc.jwt;


import java.util.HashMap;

public class AjaxResult extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";

    public static final String MSG_TAG = "msg";
    
    public static final String META_TAG = "meta";

    public static final String DATA_TAG = "data";

    /**
              *状态类型
     */
    public enum Type
    {
        /**成功*/
        SUCCESS(0),
        /**失败*/
        FAIL(1),
        ERRORA(-1),
        /**警告*/
        WARN(301),
        /**找不到**/
        MISS(404),
        /**错误*/
        ERROR(500);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }

    /**类型*/
    private Type type;

    /**编码*/
    private int code;

    /**信息*/
    private String msg;

    /**内容 */
    private Object data;

    /**
	   * 空参构造器
     */
    public AjaxResult()
    {
    }
    
    /**
              *类型 信息 内容
     */
    public AjaxResult(Type type, String msg, Object data)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        super.put(DATA_TAG, data);
    }

    /**
     * 
     * @param type 类型
     * @param msg 描述
     */
    public AjaxResult(Type type, String msg)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * @param type 类型
     * @param msg 描述
     * @param meta 元信息
     * @param data 内容
     */
    public AjaxResult(Type type, String msg, Object meta, Object data)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        super.put(META_TAG, meta);
        super.put(DATA_TAG, data);
    }

    /**
     * 
     * @return success
     */
    public static AjaxResult success()
    {
        return AjaxResult.success("success");
    }

    /**
     * @param msg 描述
     * @return msg 描述
     */
    public static AjaxResult success(String msg)
    {
        return AjaxResult.success(msg, null);
    }

    /**
     * @param msg 信息
     * @param data 内容
     * @return 成功
     */
    public static AjaxResult success(String msg, Object data)
    {
        return new AjaxResult(Type.SUCCESS, msg, data);
    }
    
    public static AjaxResult error_a(String msg)
    {
        return new AjaxResult(Type.ERRORA, msg);
    }
    
    public static AjaxResult miss(String msg)
    {
        return new AjaxResult(Type.MISS, msg);
    }

    /**
     * @param msg 信息
     * @param meta 元信息
     * @param data 内容
     * @return  success
     */
    public static AjaxResult success(String msg, Object meta, Object data)
    {
        return new AjaxResult(Type.SUCCESS, msg, meta , data);
    }

    /**
     *	 失败
     * @return fail
     */
    public static AjaxResult fail()
    {
        return AjaxResult.fail("����ʧ��");
    }

    /**
     * @param msg 信息
     * @return fail
     */
    public static AjaxResult fail(String msg)
    {
        return AjaxResult.fail(msg, null);
    }

    /**
     * @param msg 信息
     * @param data 内容
     * @return fail
     */
    public static AjaxResult fail(String msg, Object data)
    {
        return new AjaxResult(Type.FAIL, msg, data);
    }

    /**
     * warn
     * 
     * @param msg 信息
     * @return warn
     */
    public static AjaxResult warn(String msg)
    {
        return AjaxResult.warn(msg, null);
    }

    /**
     * @param msg 信息
     * @param data 内容
     * @return warn
     */
    public static AjaxResult warn(String msg, Object data)
    {
        return new AjaxResult(Type.WARN, msg, data);
    }

    /**
     * error
     * 
     * @return
     */
    public static AjaxResult error()
    {
        return AjaxResult.error("error");
    }

    /**
     *  error
     * 
     * @param msg 信息
     * @return error
     */
    public static AjaxResult error(String msg)
    {
        return AjaxResult.error(msg, null);
    }

    /**
     * error
     * 
     * @param msg 信息
     * @param data 内容
     * @return error
     */
    public static AjaxResult error(String msg, Object data)
    {
        return new AjaxResult(Type.ERROR, msg, data);
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
}

