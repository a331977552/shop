import React from 'react';
import {Button, Space} from "antd";
import {CategoryModel, RouterState} from "../../model";
import {useHistory} from "react-router-dom";

function CategoryArgs(record:CategoryModel) {
    let history = useHistory<RouterState>();

    function onClickSpec() {

        history.push({pathname:"/spec",search:"cid="+record.id,state:{updateMenu:false}})
    }

    function onAttributeClick() {
        history.push({pathname:"/attr",search:'cid='+record.id,state:{updateMenu:false}})
    }

    return (
        <Space>
            <Button style={{padding: '0 3px'}} onClick={onAttributeClick}
                    size={"small"}>属性</Button>
            <Button style={{padding: '0 3px'}} onClick={onClickSpec}
                    size={"small"}>规格</Button>
        </Space>
    );
}

export default CategoryArgs;