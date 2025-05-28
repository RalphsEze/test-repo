package ng.com.ninepsb.nip_outward.dto.response;

public record ApiResponse<T>(
        int code,
        String message,
        T data

) {

    public ApiResponse(int code, String message) {
        this(code, message, (T) "");
    }

    public ApiResponse(int code, T data) {
        this(code, SUCCESS_MESSAGE, data);
    }


    public static final int STATUS_CODE_CREATED = 201;
    public static int STATUS_CODE_SUCCESS = 200;
    public static int STATUS_CODE_UNAUTHORIZED = 401;
    public static int STATUS_CODE_FORBIDDEN = 403;
    public static int STATUS_CODE_NOT_FOUND = 404;
    public static int STATUS_CODE_CONFLICT = 409;
    public static int STATUS_CODE_BAD_REQUEST = 400;
    public static int STATUS_CODE_INTERNAL_SERVER_ERROR = 500;
    public static int STATUS_CODE_SERVICE_UNAVAILABLE = 503;

    public static String SUCCESS_MESSAGE = "success";
}
