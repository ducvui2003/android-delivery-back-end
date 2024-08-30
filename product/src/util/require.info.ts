/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 9:29 AM - 30/08/2024
 * User: lam-nguyen
 **/
import {Schema} from "mongoose";

interface RequireInfo {
    createdAt: Date;
    updateAt: Date;
    deleted: boolean
}

const RequireInfoSchema: Schema = new Schema<RequireInfo>({
    createdAt: {type: Date, default: Date.now},
    updateAt: {type: Date, default: Date.now},
    deleted: {type: Boolean, default: false}
});



export {RequireInfo, RequireInfoSchema};
