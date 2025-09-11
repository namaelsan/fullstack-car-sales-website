import { Role, User } from "./user.models";

export interface JWTToken {
    token: string;
    id: number;
}
