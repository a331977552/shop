import React, {useEffect, useState} from 'react';
import {Button, Form, Checkbox, message, Input, Space, Popconfirm, Upload} from "antd";
import TextArea from "antd/es/input/TextArea";
import {KeyVal, KeyValMix, KeyVals, ProductAttrModel, SkuKeyVal, SkuObject, SkuVal} from "../../model";
import {getProductAttrListAPI} from "../../api/ProductAttrAPI";
import styled from "styled-components";
import {loadItem, removeItem, saveItem} from "../../services";
import {getTokenFromStorage} from "../../store/TokenConfig";
import {LoadingOutlined, PlusOutlined} from "@ant-design/icons";
import {beforeImageUpload} from "../../util/UploadConfig";
import {UploadChangeParam} from "antd/lib/upload/interface";
import './attr.css'
const CheckboxGroup = Checkbox.Group;
const StyledTd = styled.td`
  border: 1px solid #dddddd;
  text-align: left;
  padding: 4px;
`
const StyledTh = styled.th`
  border: 1px solid #dddddd;
  text-align: left;
  padding: 4px;
`


const tailForm =
    {
        wrapperCol: {
            xs: {
                span: 24,
                offset: 0
            },
            sm: {
                span: 20,
                offset: 6
            }
        }
    }

declare type  TableItem = {
    title: string,
    dataIndex: string
};
const columnsPart2: TableItem[] =
    [
        {
            title: '价格',
            dataIndex: 'price',
        },
        {
            title: '库存',
            dataIndex: 'stock',
        },
        {
            title: '图片',
            dataIndex: 'img',
        }
    ]

function generateData(valueMap: KeyValMix): Array<KeyVal> {
    const data = [] as Array<{ [key: string]: string }>;
    if (Object.keys(valueMap).length === 0)
        return data;

    function _generateData(keys: string[], valueMap: KeyValMix, obj: any = {}) {
        const key = keys[0];
        let valArrayOfKey = valueMap[key];
        if (typeof valArrayOfKey === 'string') {
            valArrayOfKey = valArrayOfKey.split("\n");
        }
        valArrayOfKey.forEach(val => {
            if (keys.length === 1) {
                data.push({...obj, [key]: val, price: '', stock: '', img: ''});
            } else {
                _generateData(keys.slice(1), valueMap, {...obj, [key]: val})
            }
        });
    }

    _generateData(Object.keys(valueMap), valueMap, {});
    return data;
}

function saveAttrs(attrs: KeyVals) {
    saveItem("product_adding_attrs", JSON.stringify(attrs));
}

function loadAttrs() {
    return loadItem("product_adding_attrs");
}

function removeAttrsFromCache() {
    return removeItem("product_adding_attrs");
}


function AttrAddForm(props: {
    dataSource: SkuKeyVal[] | undefined,
    setDataSource: (dataSource: SkuKeyVal[] | undefined) => void
    category?: number
}) {
    const {setDataSource, dataSource, category} = props;
    const [productAttrs, setProductAttrs] = useState<Array<ProductAttrModel>>();
    const [attrValues, setAttrValues] = useState<KeyVals>(() => {
        console.log("attr init from cache");
        const attrs = loadAttrs();
        return attrs ? JSON.parse(attrs) : {};
    });

    const [columns, setColumns] = useState<Array<TableItem>>();
    const [attrForm] = Form.useForm();

    useEffect(() => {
        if (category) {
            if (productAttrs) {
                console.log("reset attr cache")
                setAttrValues({});
                removeAttrsFromCache();
                setColumns(undefined);
                setDataSource(undefined);
            }
            getProductAttrListAPI({example: {categoryId: category}}).then((result) => {
                const items = result.result?.items;
                setProductAttrs(items);
            }).catch((error) => {
                message.error(error.msgDetail, 3);
            })
        }
    }, [setProductAttrs, category, setAttrValues]);

    if ((productAttrs?.length || 0) === 0)
        return null;

    function onAttrValueChange(changedFields: any, allFields: KeyVals) {
        saveAttrs(allFields);
        setAttrValues(allFields);
    }

    function onRefreshClick() {
        attrForm.validateFields().then((fieldsValue: { [key: string]: string[] | string }) => {
            const columnsPart1 = Object.keys(fieldsValue).map(key => ({title: key, dataIndex: key}))
            setColumns(columnsPart1.concat(columnsPart2));
            setDataSource(generateData(fieldsValue));
        })
    }

    function onRecordChange(row: SkuKeyVal, index: number, key: string, val: SkuVal) {
        const newData = [...(dataSource as Array<SkuKeyVal>)];
        newData[index][key] = val;
        setDataSource(newData);
    }

    function onSyncAttrClick(dataIndex: string) {
        let newData = [...(dataSource as Array<KeyVal>)];
        setDataSource(newData.map(data => ({...data, [dataIndex]: newData[0][dataIndex]})));
    }


    if (!attrValues)
        return null;
    return (
        <div
            style={{
                borderWidth: '1px',
                borderStyle: 'solid',
                borderColor: '#F1F1F1',
                borderRadius: '2px',
                padding: "10px 20px",
                width: '100%',
                marginBottom: '20px'
            }}>
            <h3>产品属性</h3>
            <Form
                size={"small"}
                form={attrForm}
                style={{width: '100%'}}
                labelCol={{
                    xs: {span: 24},
                    sm: {span: 6}
                }}
                wrapperCol={{
                    xs: {span: 24},
                    sm: {span: 18},
                }}
                layout="horizontal"
                name={"attr"}
                onValuesChange={onAttrValueChange}
            >
                {
                    productAttrs?.map((attr, index) =>
                        <Form.Item key={attr.id + index} name={attr.name} label={attr.name}
                                   rules={[{required: true, message: attr.name + " 不能为空"}]}
                                   initialValue={(attrValues || {})[attr.name]}
                                   preserve={false}
                                   hasFeedback={true}
                        >
                            {attr.entryMethod === 'custom' ? <TextArea rows={4}/> :
                                <CheckboxGroup
                                    options={attr.values?.map((val) =>
                                        ({label: val.value, value: val.value}))}/>
                            }
                        </Form.Item>
                    )
                }
            </Form>
            {columns && dataSource &&
            <table style={{
                borderCollapse: 'collapse',
                width: '100%', backgroundColor: 'white'
            }}>
                <thead>
                <tr>
                    {columns.map(column => <StyledTh key={column.title}>{column.title}</StyledTh>)}
                </tr>
                </thead>
                <tbody>
                {dataSource.map((row, index) => {
                    return <tr key={index}>
                        {Object.keys(row).map(key => <StyledTd key={key}>
                            {(key === 'price' || key === 'stock') ?
                                <div><span style={{color: 'red', marginRight: '3px'}}>*</span>
                                    <Input
                                        value={row[key] as string}
                                        style={{width: '70px', margin: '0px', padding: '0px 0px'}}
                                        type={'number'} min={0} step={key === 'price' ? 0.01 : 1}
                                        onChange={(val) => {
                                            onRecordChange(row, index, key, val.target.value);
                                        }}
                                    /></div> : (key === 'img' ?
                                    <Upload
                                        listType="picture-card"
                                        className="attr-uploader"
                                        showUploadList={false}
                                        headers={{
                                            "Authorization": "Bearer " + getTokenFromStorage()
                                        }}
                                        action="/api-gateway/img-service/api/img"
                                        beforeUpload={beforeImageUpload}
                                        onChange={(info: UploadChangeParam) => {
                                            onRecordChange(row, index, key, {loading:info.file.status === 'uploading'});
                                            if (info.file.status === 'done') {
                                                onRecordChange(row, index, key, {id:info.file.response.result.id,loading:false});
                                            } else if (info.file.status === 'error') {
                                                message.error(info.file.response.msgDetail, 3)
                                            }
                                        }
                                        }
                                    >
                                        {((row[key]||{}) as SkuObject).id?
                                            <img src={"/api-gateway/img-service/api/img/" +(row[key]as SkuObject).id}
                                                 alt="avatar"
                                                 style={{width: '100%'}}/> :
                                            <div>
                                                {((row[key]||{}) as SkuObject).loading  ? <LoadingOutlined/> : <PlusOutlined/>}
                                                <div style={{marginTop: 8}}>Upload</div>
                                            </div>
                                        }
                                    </Upload>
                                    : row[key])
                            }
                        </StyledTd>)}
                    </tr>
                })}
                </tbody>
                {(dataSource.length > 1) && <tfoot>
                <tr>
                    {columns.map(col => (
                        <StyledTd key={col.dataIndex}>
                            {(col.dataIndex === 'price'
                                || col.dataIndex === 'stock') ?
                                ((dataSource.slice(1).filter(item => (!!item[col.dataIndex])).length > 0) ?
                                        <Popconfirm title={`同步将会丢失${col.title}数据,确定吗?`}
                                                    onConfirm={() => {
                                                        onSyncAttrClick(col.dataIndex);
                                                    }}
                                        >
                                            <Button style={{width: '70px'}}
                                                    size={"small"}>同步{col.title}</Button></Popconfirm> :
                                        <Button style={{width: '70px'}} onClick={() => {
                                            onSyncAttrClick(col.dataIndex);
                                        }} size={"small"}>同步{col.title}</Button>
                                ) : ""}</StyledTd>
                    ))
                    }
                </tr>
                </tfoot>}
            </table>
            }
            <Form.Item
                style={{marginTop: '10px', marginBottom: '0px'}}
                {...tailForm}
            >
                <Space>
                    {(dataSource?.length || 0) > 0 ?
                        <Popconfirm title={"重新生成列表,会导致当前数据丢失!"}
                                    onConfirm={onRefreshClick}
                        >
                            <Button type={'primary'} danger>重新生成列表
                            </Button>
                        </Popconfirm> : <Button type={'primary'} onClick={onRefreshClick}>生成列表
                        </Button>
                    }
                </Space>
            </Form.Item>
        </div>
    );
}

export default AttrAddForm;