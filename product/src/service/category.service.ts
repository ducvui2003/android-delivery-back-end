/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 4:27 pm
 * User: ducvui2003
 **/
import CategoryModel from "../model/category.model";
import CategoryRepository from "../repository/category.repository";
import mongoose from "mongoose";
import AppError from "../util/error/AppError";
import Mapper from "../util/mapper";


const getById = async (id: string): Promise<CategoryModel> => {
    const data = await CategoryRepository.findById(id)
    return Mapper.convert<CategoryModel>(data, convertToModel)
}

const getAll = async (): Promise<CategoryModel[]> => {
    const data = await CategoryRepository.findAll()
    return Mapper.convertArray<CategoryModel>(data, convertToModel)
}

const create = async (category: CategoryModel): Promise<CategoryModel> => {
    try {
        const data = await CategoryRepository.save(category)
        return Mapper.convert<CategoryModel>(data, convertToModel)
    } catch (err) {
        let error;
        const mongooseError = err as mongoose.Error
        if (mongooseError instanceof mongoose.Error.ValidatorError) error = AppError.VALIDATION
        else error = AppError.DUPLICATE
        error.stack = mongooseError.stack
        throw error
    }
}

const convertToModel = (data: any): CategoryModel => {
    return {
        _id: data._id,
        name: data.name
    }
}

export default {getById, getAll, create, convertToModel}
