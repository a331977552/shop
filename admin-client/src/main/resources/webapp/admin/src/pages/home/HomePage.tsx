import React, { useEffect} from 'react';
import {Card, Col, Row, Spin, Statistic} from "antd";
import {getHomeInfo, selectHomeReducer} from "../../store/slices/homeSlice";
import { ArrowUpOutlined, ArrowDownOutlined } from '@ant-design/icons';
import {useAppDispatch, useAppSelector} from "../../store/hooks";



function HomePage() {
    let dispatch = useAppDispatch();
    let homeReducer = useAppSelector(selectHomeReducer);
    useEffect(()=>{
     dispatch(getHomeInfo(null));

    },[dispatch]);
    return (
        <Spin spinning={homeReducer.status==='loading'}>
            <div>
                <Row gutter={16}>
                    <Col span={12}>
                        <Card>
                            <Statistic
                                title="Active"
                                value={11.28}
                                precision={2}
                                valueStyle={{ color: '#3f8600' }}
                                prefix={<ArrowUpOutlined />}
                                suffix="%"
                            />
                        </Card>
                    </Col>
                    <Col span={12}>
                        <Card>
                            <Statistic
                                title="Idle"
                                value={9.3}
                                precision={2}
                                valueStyle={{ color: '#cf1322' }}
                                prefix={<ArrowDownOutlined />}
                                suffix="%"
                            />
                        </Card>
                    </Col>
                </Row>
            </div>
        </Spin>
    );
}

export default HomePage;








