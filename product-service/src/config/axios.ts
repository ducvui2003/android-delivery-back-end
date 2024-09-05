/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 2:57 pm
 * User: ducvui2003
 **/
import axios, {AxiosInstance, AxiosResponse, HttpStatusCode, InternalAxiosRequestConfig} from "axios";

const API_RATING: AxiosInstance = axios.create({
    baseURL: process.env.PROTOCOL + '://' + process.env.HOST + ':' + process.env.API_PORT + '/api/v1/rating',
    headers: {
        "Access-Control-Allow-Origin": "*",
    },
})

API_RATING.interceptors.request.use(async (config: InternalAxiosRequestConfig) => {
    try {
        return config;
    } catch (error) {
        // Network Error
        console.error("API request error:", error);
        return Promise.reject(error);
    }
});

API_RATING.interceptors.response.use(
    (response: AxiosResponse) => {
        switch (response.data.statusCode) {
            case HttpStatusCode.Ok:
                return response.data;
            case HttpStatusCode.BadRequest:
                console.error("Bad request", response.data.data);
                throw new Error(response.data.error);
            default:
                console.warn(response.data);
                break;
        }

        if(response.data.statusCode > 400) throw new Error(response.data.message);
    },
    error => {
        //Network
        if (!error.response) {
            console.log("Network Error:", error.message);
        }
        // Timeout
        if (error.code === "ECONNABORTED") console.log("Request timed out:", error.message);
        //Error from server
        if (error.response && error.response.status >= 400) console.error("API response error:", error);
        return error;
    }
);

export interface ApiResponse<T> {
    statusCode: number;
    error?: string;
    message: string;
    data: T;
}

export default API_RATING;
