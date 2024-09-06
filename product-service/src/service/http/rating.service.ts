/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 6/9/24 - 2:56 pm
 * User: ducvui2003
 **/
import HTTP_RATING_INSTANCE from "../../config/http/rating.http";
import {RatingModel} from "../../model/product.model";

const getRatingOverall = async (productId: string): Promise<RatingModel> => {
    try {
        const response = await HTTP_RATING_INSTANCE.get(`/review/product/${productId}`);
        return response.data;
    } catch (error) {
        throw error;
    }
}

export default {
    getRatingOverall
}