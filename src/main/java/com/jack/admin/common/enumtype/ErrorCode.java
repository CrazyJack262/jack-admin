package com.jack.admin.common.enumtype;

/**
 * @author crazyjack262
 */
public enum ErrorCode {

    // 2000-->Success!
    // 6000-->Fail！
    // 6000也可以作为自定义错误信息状态码

    // common
    COMMON_ERROR("Fail", 500),
    COMMON_PARAMS_ERR("提交参数不合法", 6001),
    COMMON_PARAMS_ID_ERR("提交参数ID不合法", 6002),
    COMMON_EMPTY_CONDITION_RESULT("没有找到符合条件的数据", 6003),
    COMMON_PARAMS_NOT_EXIST("提交的字段不存在,或者参数格式错误", 6004),
    COMMON_PARAMS_REQUIRED("缺少必填参数", 6005),
    COMMON_REQUEST_NOT_SUPPORTED("请求方式错误", 6006),

    // sql
    COMMON_SQL_ERROR("mysql通用错误", 6100),
    COMMON_SQL_INSERT_FAIL("增加失败", 6101),
    COMMON_SQL_DELETE_FAIL("删除失败", 6102),
    COMMON_SQL_UPDATE_FAIL("修改失败", 6103),
    COMMON_SQL_NAME_EXIST("该名称已存在", 6104),
    COMMON_SQL_ID_NOT_EXIST("主键ID不能为空", 6105),
    COMMON_SQL_VERSION_NOT_EXIST("数据版本version不能为空", 6106),

    // io
    COMMON_IO_ERROR("io通用错误", 6200),
    COMMON_FILE_NOT_EXIST("文件没找到，请联系管理员", 6201),
    COMMON_FILE_DATA_NULL("文档中不不存在有效的数据", 6202),
    COMMON_FILE_DATA_ERR("文档中的数据格式错误", 6203),

    // form
    COMMON_INVALID_PASSWORD("密码格式错误", 6300),
    COMMON_INVALID_EMAIL("邮件格式错误", 6301),
    COMMON_INVALID_NAME("账号格式错误", 6302),
    COMMON_INVALID_PARAMS("填写字段不合法", 6303),

    // shiro-login
    NO_LOGIN("用户未登录", 401),
    UNAUTHORIZED("权限不足", 7001),
    ADMIN_ONLY("只有管理员账号可以调用这个接口", 6402),
    NO_PERSSIOM("没有权限请求", 6403),
    WRONG_ACCOUNT_OR_PSW("账号或密码错误", 6404),
    WRONG_ACCOUNT_PSW("账号密码错误", 6405),
    USER_NOT_EXISTS("用户不存在", 6406),
    USER_ACCOUNT_LOCK("账号已被锁定，请半小时后重新登录，或者联系管理员处理", 6407),
    USER_ACCOUNT_STOP("您的账号已被停用", 6408),
    USER_ACCOUNT_EXPIRED("凭证已过期", 6409),

    // uploading
    COMMON_UPLOAD_FILE_TYPE_ERROR("上传文件格式错误", 6500),
    COMMON_UPLOAD_FILE_UPLOADING("uploading", 6501),
    COMMON_UPLOAD_FILE_NOT_EXIST("文件不存在", 6502),
    COMMON_UPLOAD_FILE_TO_DISK_ERR("写入盘符异常", 6504),
    COMMON_UPLOAD_FILE_SIZE_MAX("上传的文件大小不能超过100MB", 6503),

    // es
    ES_BIG_PAGE_SEARCH("单页查询数据不能超过10000!", 9000);

    public final String msg;
    public final int code;

    ErrorCode(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
