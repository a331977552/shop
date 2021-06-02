import React, {useState} from 'react';
import {Form, Input, Button, Checkbox} from 'antd';
import { useAppSelector, useAppDispatch } from '../store/hooks';
import {RootState} from "../store/store";
import {login} from "../store/user/UserSlice";

// import {selectCount} from "../features/counter/counterSlice";

const layout = {
    labelCol: {span: 8},
    wrapperCol: {span: 16},
};
const tailLayout = {
    wrapperCol: {offset: 8, span: 16},
};

function LoginPage(props:any) {

    const count = useAppSelector((state: RootState) => state.user.user);
    const dispatch = useAppDispatch();
    const [incrementAmount, setIncrementAmount] = useState('2');

    const onFinish = (values: any) => {
        console.log('Success:', values);
        dispatch(login(values));
    };

    const  onFinishFailed = (errorInfo: any) => {
        console.log('Failed:', errorInfo);
    };

        return (
            <div style={{display:"flex",height:'100vh',justifyContent:"center", alignItems:'center'}}>
            <Form
                {...layout}
                name="basic"
                initialValues={{remember: true}}
                onFinish={onFinish}
                onFinishFailed={onFinishFailed}
            >
                <Form.Item
                    label="Username"
                    name="username"
                    rules={[{required: true, message: 'Please input your username!'}]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    label="Password"
                    name="password"
                    rules={[{required: true, message: 'Please input your password!'}]}
                >
                    <Input.Password/>
                </Form.Item>

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
        );
}

export default LoginPage;
