import React from 'react';
import {BrandModel} from "../../model";
import {message, Space, Switch} from "antd";
import {useAppDispatch} from "../../store/hooks";
import {updateBrand} from "../../store/slices/brandSlice";
import {updateBrandAPI} from "../../api";


function BrandSetting(record: BrandModel) {
    let dispatch = useAppDispatch();
    function onVisibleChanged() {
        const key = 'brand_update_key';
        message.loading({content:'修改中...',key})
        updateBrandAPI({...record,visible:!record.visible}).then(()=>{
            message.success({content:'成功',duration:1,key})
            dispatch(updateBrand({...record,visible:!record.visible}))
        }).catch((error)=>{
            console.log(error)
            message.error({content:'修改失败,原因: '+error.msgDetail,duration:3,key})
        })
    }

    return (
        <Space>
            <Switch checkedChildren="显示" unCheckedChildren="隐藏" checked={record.visible}
                    onClick={onVisibleChanged}
            />
        </Space>
    );
}

export default BrandSetting;