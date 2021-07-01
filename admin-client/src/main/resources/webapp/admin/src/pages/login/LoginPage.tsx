import React, { useState} from 'react';
import {Form, Input, Button, Checkbox} from 'antd';
import {Spin} from 'antd';
import {useHistory} from 'react-router-dom';
import loginAPI from "../../api/UserAPI";
import {setTokenToStorage} from "../../store/TokenConfig";
import {RouterState} from "../../model";
import {parseSearchParams} from "../../services";
import {UserOutlined,LockOutlined} from "@ant-design/icons";


const layout = {
    labelCol: {span: 0},
    wrapperCol: {span: 24},
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
            const param = parseSearchParams(history.location.search);
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
                <div style={{marginBottom:'2vh'}}><h1 style={{fontWeight:'bold',fontSize:'xxx-large'}}><span style={{color:'#50C11D'}}>C</span>ody's <span style={{color:'#50C11D'}}>S</span>hop</h1></div>
                <Form
                    {...layout}
                    style={{width:'250px'}}
                    name="basic"
                    initialValues={{remember: true}}
                    onFinish={onFinish}
                >
                    <Form.Item
                        name="username"
                        rules={[{required: true, message: '用户名不能为空'}]}
                    >
                        <Input prefix={<UserOutlined />} placeholder={"用户名"}/>
                    </Form.Item>

                    <Form.Item
                        name="password"
                        rules={[{required: true, min: 6, max: 50, message: '密码长度不正确'}]}
                    >
                        <Input.Password prefix={<LockOutlined />}  placeholder={"密码"}/>
                    </Form.Item>

                    <div style={{color: '#ff4d4f'}}>
                        {status === 'error' && errorMsg}
                    </div>

                    <Form.Item {...tailLayout} name="remember" valuePropName="checked">
                        <Checkbox>记住我</Checkbox>
                    </Form.Item>

                    <Form.Item {...tailLayout}>
                        <Button type="primary" htmlType="submit">
                            登录
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
