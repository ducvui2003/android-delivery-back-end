/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 4:27 pm
 * User: ducvui2003
 **/
import ProductRepository from "../repository/product.repository";
import {NutritionalModel, ProductModel} from "../model/product.model";
import Mapper from "../util/mapper";
import {DiscountInfoDocument, ProductDocument} from "../document/product.document";
import CategoryService from "./category.service";
import ProductOptionService from "./productOption.service";
import AppError from "../util/error/AppError";


const getById = async (id: string) => {
    const data = await ProductRepository.findById(id)
    if (!data) throw AppError.NOT_FOUND
    return Mapper.convert<ProductModel>(data, convertToModel)
}

const getByAll = async () => {
    const data = await ProductRepository.findAll()
    return Mapper.convertArray<ProductModel>(data, convertToModel)
}

const create = async (product: ProductDocument) => {
    const data = await ProductRepository.save(product)
    return Mapper.convert<ProductModel>(data, convertToModel)
}

const removeDiscount = async (id: string) => {
    const data = await ProductRepository.removeDiscount(id)
    if (!data) throw AppError.NOT_FOUND
    return Mapper.convert<ProductModel>(data, convertToModel)
}

const setDiscount = async (id: string, discountInfo: DiscountInfoDocument) => {
    const data = await ProductRepository.setDiscount(id, discountInfo)
    if (!data) throw AppError.NOT_FOUND
    return Mapper.convert<ProductModel>(data, convertToModel)
}

const convertToModel = (data: any): ProductModel => {
    const dataFormat: ProductModel = {
        id: data._id,
        name: data.name,
        price: data.price,
        quantity: data.amount,
        description: data.description,
        category: CategoryService.convertToModel(data.category),
        options: data.options.map(ProductOptionService.convertToModel),
        nutritional: data.nutritional.map((item: any) => {
            return {
                name: item.name,
                value: item.value,
                unit: item.unit
            } as NutritionalModel
        })
    }

    if (data.discountInfo) dataFormat.discountInfo = {
        discount: data.discountInfo.discount,
        expired: data.discountInfo.expired
    }

    return dataFormat
}

export default {getById, create, getByAll, removeDiscount, convertToModel, setDiscount}
