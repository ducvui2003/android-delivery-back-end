/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 6:00 pm
 * User: ducvui2003
 **/

class AppError extends Error {
    private readonly _statusCode: number;
    static INVALID_INPUT = new AppError(1001, "Invalid input provided");
    static NOT_FOUND = new AppError(1002, "Resource not found");
    static UNAUTHORIZED = new AppError(1003, "Unauthorized access");
    static DUPLICATE = new AppError(1004, "Resource already exists");
    static VALIDATION = new AppError(1005, "Validation error");

    constructor(statusCode: number, message: string) {
        super(message);
        this._statusCode = statusCode;
        Error.captureStackTrace(this, this.constructor);
    }

    get statusCode(): number {
        return this._statusCode;
    }
}

export default AppError
