import React, {useEffect, useState} from 'react';
import {ContentState, convertToRaw, EditorState, RichUtils} from "draft-js";
import {Editor} from "react-draft-wysiwyg";
import "react-draft-wysiwyg/dist/react-draft-wysiwyg.css";
import htmlToDraft from 'html-to-draftjs';
import draftToHtml from 'draftjs-to-html';
import {saveItem} from "../../services";
import {debounce} from "../../util/TimerUtils";


function DetailEditor(props:{editorState: EditorState,
                      setEditorState:(editorState: EditorState)=>void}
                      ) {
    const {editorState,setEditorState}= props;
    let editor = {};



    return (
        <div >
            <h3>商品详情</h3>
            <Editor
                editorState={editorState}
                editorStyle={{
                    minHeight:'200px',
                    borderWidth: '1px',
                    borderStyle: 'solid',
                    borderColor: '#F1F1F1',
                    borderRadius: '2px'
                }}
                editorRef={(ref)=>{
                    editor = ref;
                }}
                onEditorStateChange={setEditorState}
            />



        </div>
    );
}

export default DetailEditor;