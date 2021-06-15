import React from 'react';
import {Button, Space} from "antd";
import {CategoryModel} from "../../model";
import {useHistory} from "react-router-dom";

function CategoryArgs(record:CategoryModel) {
    let history = useHistory();

    function onClickSpec() {
        history.push("/product/spec?cid="+record.id)
    }

    function onAttributeClick() {
        history.push("/product/attr?cid="+record.id)
    }

    return (
        <Space>
            <Button style={{padding: '0 3px'}} onClick={onAttributeClick}
                    size={"small"}>属性</Button>
            <Button style={{padding: '0 3px'}} onClick={onClickSpec}
                    size={"small"}>参数</Button>
        </Space>
    );
}

export default CategoryArgs;