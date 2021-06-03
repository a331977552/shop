import React, {useState} from 'react';
import {Form, Input, Button, Checkbox} from 'antd';
import {useAppSelector, useAppDispatch} from '../store/hooks';
import {RootState} from "../store/store";
import {login} from "../store/user/UserSlice";
import {Spin} from 'antd';
import {setTokenToCommonHeader} from "../http/HttpConfig";
import {setToken} from "../http/TokenConfig";
import { useHistory } from 'react-router-dom';

// import {selectCount} from "../features/counter/counterSlice";

const layout = {
    labelCol: {span: 8},
    wrapperCol: {span: 16},
};
const tailLayout = {
    wrapperCol: {offset: 8, span: 16},
};

function LoginPage() {

    const user = useAppSelector((state: RootState) => state.userReducer.token);
    const {token,status,errorMsg}=  user;
    const history = useHistory();
    const dispatch = useAppDispatch();

    if (token){
        setToken(token);
        setTokenToCommonHeader();
        history.push("/");
        return <></>
    }

    const onFinish = (values: any) => dispatch(login(values));
    return (
        <Spin spinning={status === 'loading'}>
            <div style={{display: "flex", height: '100vh', justifyContent: "center", alignItems: 'center'}}>
                <Form
                    {...layout}
                    name="basic"
                    initialValues={{remember: true}}
                    onFinish={onFinish}
                >
                    <Form.Item
                        label="Username"
                        name="username"
                        rules={[{required: true, message: '用户名不能为空'}]}
                    >
                        <Input/>
                    </Form.Item>

                    <Form.Item
                        label="Password"
                        name="password"
                        rules={[{required:true,min:6,max:50, message:'密码长度不正确'}]}
                    >
                        <Input.Password/>
                    </Form.Item>

                    <div style={{color:'#ff4d4f'}} >
                        {errorMsg}
                    </div>

                    <Form.Item {...tailLayout} name="remember" valuePropName="checked">
                        <Checkbox>Remember me</Checkbox>
                    </Form.Item>

                    <Form.Item {...tailLayout}>
                        <Button type="primary" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>

                </Form>
            </div>
        </Spin>
    );
}

export default LoginPage;
