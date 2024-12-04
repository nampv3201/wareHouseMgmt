package com.datn.warehousemgmt.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(0, "Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR),

    USER_EXISTED(0, "Tài khoản đã tồn tại", HttpStatus.BAD_REQUEST),

    USER_NOT_EXISTED(0, "Tài khoản không tồn tại", HttpStatus.NOT_FOUND),

    UNAUTHENTICATED(0, "Yêu cầu xác thực tài khoản", HttpStatus.UNAUTHORIZED),

    INVALID_ACCOUNT(0, "Sai tài khoản hoặc mật khẩu", HttpStatus.BAD_REQUEST),

    CREATION_FAILED(0, "Khởi tạo không thành công", HttpStatus.BAD_REQUEST),
    UPDATE_FAILED(0, "Cập nhật không thành công", HttpStatus.BAD_REQUEST),
    DELETE_FAILED(0, "Xóa không thành công", HttpStatus.BAD_REQUEST),
    IMPORT_FAILED(0, "Import không thành công", HttpStatus.BAD_REQUEST),

    PRODUCT_NOT_FOUND(0, "Không tìm thấy sản phẩm", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND(0, "Không tìm thấy danh mục", HttpStatus.NOT_FOUND),
    BATCH_NOT_FOUND(0, "Không tìm thấy thông tin lô", HttpStatus.NOT_FOUND),

    FILE_NOT_FOUND(0, "Không tìm thấy file", HttpStatus.NOT_FOUND),
    IO_EXCEPTION(0, "Xảy ra lỗi trong quá trình đọc file", HttpStatus.NOT_FOUND),
    SESSION_EXPIRED(0, "Session timeout", HttpStatus.BAD_REQUEST),

    NAME_NOT_NULL(0, "Tên không được để trống", HttpStatus.BAD_REQUEST),
    ADDRESS_NOT_NULL(0, "Địa chỉ không được để trống", HttpStatus.BAD_REQUEST),
    WRONG_INPUT(0, "Vui lòng nhập đủ các trưng thông tin", HttpStatus.BAD_REQUEST),

    FILE_NOT_ALLOWED(0, "Định dạng file không được cho phép", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    FILE_FALSE_FORMAT(0, "Định dạng file không đúng theo template", HttpStatus.BAD_REQUEST),
    WRONG_INPUT1(0, "Vui lòng nhập đủ các trưng thông tin", HttpStatus.BAD_REQUEST),
    WRONG_INPUT2(0, "Vui lòng nhập đủ các trưng thông tin", HttpStatus.BAD_REQUEST),

    ;


    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

}