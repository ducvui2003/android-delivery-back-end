/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 4:27 pm
 * User: ducvui2003
 **/
import ProductOptionRepository from "../repository/productOption.repository";
import {GroupOptionModel, OptionModel} from "../model/productOption.model";
import Mapper from "../util/mapper";


const getById = async (id: string): Promise<OptionModel | GroupOptionModel> => {
    const data = await ProductOptionRepository.findById(id)
    return Mapper.convert<OptionModel | GroupOptionModel>(data, convertToModel)
}

const getAll = async (): Promise<(OptionModel | GroupOptionModel)[]> => {
    const data = await ProductOptionRepository.findAll()
    return Mapper.convertArray<OptionModel | GroupOptionModel>(data, convertToModel)
}

const create = async (option: OptionModel | GroupOptionModel): Promise<OptionModel | GroupOptionModel> => {
    const data = await ProductOptionRepository.save(option)
    return Mapper.convert<OptionModel | GroupOptionModel>(data, convertToModel) as OptionModel | GroupOptionModel
}

const convertToModel = (data: any): OptionModel | GroupOptionModel => {
    if (data.price) return {
        id: data._id,
        name: data.name,
        price: data.price
    } as OptionModel

    return {
        id: data._id,
        name: data.name,
        options: data.options.map(convertToModel)
    } as GroupOptionModel
}


export default {getById, getAll, create, convertToModel}
