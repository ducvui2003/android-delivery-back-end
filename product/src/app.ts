import express, {Request, Response} from 'express';
import envConfig from "./config/environment";
import {CONNECT_DB} from "./config/database";
import {APIs_V1_PRODUCT} from "./route/v1";
import formatResponseMiddleware from "./middleware/formatResponse.middleware";
import errorHandlerMiddleware from "./middleware/errorHandler.middleware";

const START_SERVER = () => {
    const app = express();
    const PORT = envConfig.PORT;

    app.get('/', (req: Request, res: Response) => {
        res.send('Hello, TypeScript with Express!');
    });

    app.use(express.json());

    app.use('/api', APIs_V1_PRODUCT);

    // Format Data
    app.use(formatResponseMiddleware);

    // Error Handler
    app.use(errorHandlerMiddleware);


    app.listen(PORT, () => {
        console.log(`Server is running on http://localhost:${PORT}`);
    });

}

CONNECT_DB()
    .then(() => console.log("Connected to database"))
    .then(() => START_SERVER())
    .catch(error => {
        console.error("error", error)
        process.exit(0);
    })

