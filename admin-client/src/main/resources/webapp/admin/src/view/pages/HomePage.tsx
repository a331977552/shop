import React, {Component} from 'react';
import {Spin} from "antd";
import { connect, ConnectedProps } from 'react-redux'
import {getHomeInfo, HomeInfo, homeSlice, selectHomeReducer} from "../../store/slices/HomeSlice";
import {log} from "../../services";
import {RootState} from "../../store/store";



class HomePage extends Component<PropsFromRedux> {


    componentDidMount() {
        this.props.getHomeInfo({});
    }

    render() {
        console.log(this.props)
        return (
            <Spin spinning={true}>

            </Spin>
        );
    }
}


const mapState = (state: RootState) => {
    log(state)
    return selectHomeReducer(state);
}

const connector = connect(mapState,
    {...homeSlice.actions,getHomeInfo})

type PropsFromRedux = ConnectedProps<typeof connector>
export default connector(HomePage);




