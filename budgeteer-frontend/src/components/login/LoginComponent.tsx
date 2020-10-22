import React from "react";
import {Button, Checkbox, Form, Input} from 'antd';
import axios from 'axios';

const layout = {
    labelCol: {span: 8},
    wrapperCol: {span: 16},
};
const tailLayout = {
    wrapperCol: {offset: 8, span: 16},
};

const LoginComponent = () => {
    const onFinish = (values: { username: string, password: string, remember: boolean }) => {
        axios.post('http://localhost:8080/api/v1/login', {
            userName: values.username,
            passCode: values.password
        }).then(response => {
            console.log(response);
        }).catch(err => {
            console.log(err);
        })
    };

    const onFinishFailed = (errorInfo: unknown) => {
        console.log('Failed:', errorInfo);
    };
    return (
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
    );
};

export default LoginComponent;