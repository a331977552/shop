import React from 'react';
import {KeyValMix, ProductModel} from "../../../model";
import {Table} from "antd";

function SpecsContent(record:ProductModel) {

    const specs = JSON.parse(record.specs || "{}") as KeyValMix;
    const columns = Object.keys(specs).map(spec => ({
        key: spec,
        title: spec,
        dataIndex: spec,
        render(text: string | string[], record: KeyValMix, index: number) {
            if (typeof text === 'string') {
                text = [text];
            }
            return text.join(",");
        }
    }));
    const dataSource = [specs];
    return (
        <Table
            scroll={{y: 240}}
            style={{width: '100%', maxHeight:'400px', overflow: 'auto'}} rowKey={(record: any) => JSON.stringify(record)} columns={columns}
            dataSource={dataSource}
            pagination={false}
        />
    );
}

export default SpecsContent;