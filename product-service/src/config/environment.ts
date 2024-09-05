/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 2:42 pm
 * User: ducvui2003
 **/
import dotenv from "dotenv";
import path from "path";

dotenv.config({path: path.resolve(__dirname, '../../.env')});
if (process.env.NODE_ENV === 'development')
    dotenv.config({path: path.resolve(__dirname, '../../.env.dev')});

console.info(`Running in ${process.env.NODE_ENV} mode`);


const envConfig = {
    HOST: process.env.APP_HOST,
    PORT: Number(process.env.APP_PORT),
    MONGODB_URI: process.env.MONGODB_URI as string,
    DATABASE: process.env.DATABASE,
    NODE_ENV: process.env.NODE_ENV,
}

export function isDevelopment(): boolean {
    return envConfig.NODE_ENV === 'development';
}

export default envConfig
