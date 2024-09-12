import express, {Request, Response} from 'express';
import envConfig from "./config/environment";
import FormatResponseMiddleware from "./middleware/formatResponse.middleware";
import ErrorHandlerMiddleware from "./middleware/errorHandler.middleware";
import CONNECT_DB from "./config/database.mongoose";
import APIs_V1 from "./route/v1";
import LogMiddleware from "./middleware/log.middleware";
import ClearEmptyValueMiddleware from "./middleware/clearEmptyValue.middleware";

const START_SERVER = () => {
    const app = express();
    const PORT = envConfig.PORT;

    app.get('/', (_: Request, res: Response) => {
        res.send('Hello, TypeScript with Express!');
    });

    app.use(express.json());
    app.use(LogMiddleware);
    app.use('/api', APIs_V1);
    app.use(FormatResponseMiddleware);
    app.use(ErrorHandlerMiddleware);
    app.use(ClearEmptyValueMiddleware);

    app.listen(PORT, () => {
        console.log(`Server is running on http://localhost:${PORT}`);
    });

}

CONNECT_DB()
    .then(() => START_SERVER())
    .catch(error => {
        console.error("error", error)
        process.exit(0);
    })

