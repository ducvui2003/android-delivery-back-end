/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 2:28 pm
 * User: ducvui2003
 **/
import {MongoClient, ServerApiVersion, MongoClientOptions, Db} from "mongodb";
import envConfig from "./environment";

let database: Db | null = null;
const clientInstance: MongoClient = new MongoClient(envConfig.MONGODB_URI, {
    serverApi: {
        version: ServerApiVersion.v1,
        strict: true,
        deprecationErrors: true,
    }
});

const CONNECT_DB = async () => {
    await clientInstance.connect();
    database = clientInstance.db(envConfig.DATABASE);
};

// Reuse instance connection
const GET_DB = () => {
    if (!database) throw new Error("Database is not connected");
    return database;
}

export {
    CONNECT_DB, GET_DB
}