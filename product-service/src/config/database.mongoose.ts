/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 9:01 AM - 30/08/2024
 * User: lam-nguyen
 **/


import mongoose from "mongoose";
import envConfig from "./environment";
import {ServerApiVersion} from "mongodb";

async function connectToDatabase() {
    try {
        await mongoose.connect(envConfig.MONGODB_URI, {
            serverApi: ServerApiVersion.v1,
            dbName: envConfig.DATABASE,
        });
        console.log("Kết nối thành công đến MongoDB!");
    } catch (error) {
        console.error("Lỗi khi kết nối đến MongoDB:", error);
        throw error
    }
}

export default connectToDatabase;
