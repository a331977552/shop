import React, { useState} from 'react';
import {Form, Input, Button, Checkbox} from 'antd';
import {Spin} from 'antd';
import {useHistory} from 'react-router-dom';
import loginAPI from "../../api/UserAPI";
import {setTokenToStorage} from "../../store/TokenConfig";
import {RouterState} from "../../model";
import {paramParser} from "../../services";


const layout = {
    labelCol: {span: 8},
    wrapperCol: {span: 16},
};
const tailLayout = {
    wrapperCol: {offset: 8, span: 16},
};

function LoginPage() {
    const [status,setStatus] = useState("");
    const [errorMsg,setErrorMsg] = useState(null);
    let history = useHistory<RouterState>();

    const onFinish = (values: any) => {
        setStatus("loading")
        loginAPI(values).then(response=>{
            let result = response.result as string;
            setTokenToStorage(result);
            const param = paramParser(history.location.search);
            const redirect_url =param["redirect_url"] as string;
            if (redirect_url){
                window.location.replace(decodeURI(redirect_url));
            }else {
                history.push({pathname:"/",state:{updateMenu:true}})
            }
        }).catch(reason => {
            setStatus("error")
            setErrorMsg(reason.msgDetail);
        });
    }

    return (
        <Spin spinning={status === 'loading'}>
            <div style={{display: "flex", height: '100vh', flexDirection:'column', justifyContent: "center", alignItems: 'center'}}>
                <div style={{marginBottom:'2vh'}}><h1>商城管理系统</h1></div>
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
                        rules={[{required: true, min: 6, max: 50, message: '密码长度不正确'}]}
                    >
                        <Input.Password/>
                    </Form.Item>

                    <div style={{color: '#ff4d4f'}}>
                        {status === 'error' && errorMsg}
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
                <div style={{position:'fixed',bottom:'2vh'}}>Developed by Cody, Email:
                    &nbsp;<a href="mailto:a13521874221@gmail.com">a13521874221@gmail.com</a>
                    </div>
            </div>
        </Spin>
    );
}

export default LoginPage;
