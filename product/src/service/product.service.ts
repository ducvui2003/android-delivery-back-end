/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 4:27 pm
 * User: ducvui2003
 **/
import ProductRepository from "../repository/product.repository";
import {ProductModel} from "../model/product.model";
import CategoryService from "./category.service";
import ProductOptionService from "./productOption.service";
import Mapper from "../util/mapper";


const getProduct = async (id: string) => {
    const data = await ProductRepository.findById(id)
    return Mapper.convert<ProductModel>(data, convertToModel)
}

const createProduct = async (product: ProductModel) => {
    await ProductRepository.save(product)
}

const convertToModel = (data: any): ProductModel => {
    return {
        _id: data._id,
        name: data.name,
        price: data.price,
        category: CategoryService.convertToModel(data.category),
        amount: data.amount,
        description: data.description,
        discountInfo: {
            discount: data.discountInfo.discount,
            expired: data.discountInfo.expired
        },
        options: data.options.map(ProductOptionService.convertToModel),
        nutritional: {
            name: data.nutritional.name,
            value: data.nutritional.value,
            unit: data.nutritional.unit
        }
    }
}

export default {getProduct, createProduct, convertToModel}
