/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 1:02 PM - 31/08/2024
 * User: lam-nguyen
 **/


const convert = <T>(data: any, functionConvert: (data: any) => T): T => {
    if (data instanceof Array) throw new Error("Data is array")
    return functionConvert(data.toObject())
}

const convertArray = <T>(data: any[], functionConvert: (data: any[]) => T): T[] => {
    return data.map(doc => functionConvert(doc.toObject()));
}

export default {convert, convertArray}
