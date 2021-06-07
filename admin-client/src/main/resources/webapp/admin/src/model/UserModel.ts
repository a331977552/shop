
export  interface UserModel {
    id: string;
    username: string,
    email: string;
    phone: string;
    alias?: string;
    avatar?: string;
    dateOfBirth: Date;
    role: Date;
    createdTime: Date;
    updatedTime: Date;
};