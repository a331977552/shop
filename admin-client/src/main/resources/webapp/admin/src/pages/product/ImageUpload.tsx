import React, {useState} from 'react';
import {message, Upload} from "antd";
import {getTokenFromStorage} from "../../store/TokenConfig";
import {beforeImageUpload} from "../../util/UploadConfig";
import {LoadingOutlined, PlusOutlined} from "@ant-design/icons";
import {UploadChangeParam} from "antd/lib/upload/interface";

function ImageUpload({img,onUploadFinished}: {onUploadFinished:(imgID:string,info?:UploadChangeParam)=>void, img?: string }) {
    const [imgUploading, setImgUploading] = useState(false);

    const onFileUploading = (info: UploadChangeParam) => {
        setImgUploading(info.file.status === 'uploading');
        if (info.file.status === 'done') {
            onUploadFinished(info.file.response.result.id,info);
        } else if (info.file.status === 'error') {
            message.error(info.file.response.msgDetail, 3)
        }
    };
    return (
        <Upload
            listType="picture-card"
            className="avatar-uploader"
            showUploadList={false}
            headers={{
                "Authorization": "Bearer " + getTokenFromStorage()
            }}
            action="/api-gateway/img-service/api/img"
            beforeUpload={beforeImageUpload}
            onChange={onFileUploading}

        >
            {img ?
                <img src={"/api-gateway/img-service/api/img/" + img} alt="avatar"
                     style={{width: '100%'}}/> :
                <div>
                    {imgUploading ? <LoadingOutlined/> : <PlusOutlined/>}
                    <div style={{marginTop: 8}}>Upload</div>
                </div>
            }
        </Upload>
    );
}

export default ImageUpload;