import React, {Component} from 'react';
import {Card, Col, Row, Spin, Statistic} from "antd";
import { connect, ConnectedProps } from 'react-redux'
import {getHomeInfo, homeSlice, selectHomeReducer} from "../../store/slices/homeSlice";
import {RootState} from "../../store/store";
import { ArrowUpOutlined, ArrowDownOutlined } from '@ant-design/icons';



class HomePage extends Component<PropsFromRedux> {


    componentDidMount() {
        this.props.getHomeInfo({});
    }

    render() {
        return (
            <Spin spinning={this.props.status === 'loading'}>
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
}


const mapState = (state: RootState) => {
    return selectHomeReducer(state);
}

const connector = connect(mapState,
    {...homeSlice.actions,getHomeInfo})

type PropsFromRedux = ConnectedProps<typeof connector>
export default connector(HomePage);




