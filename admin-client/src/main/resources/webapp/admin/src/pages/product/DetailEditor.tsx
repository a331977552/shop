import React from 'react';
import {EditorState} from "draft-js";
import {Editor} from "react-draft-wysiwyg";
import "react-draft-wysiwyg/dist/react-draft-wysiwyg.css";


function DetailEditor(props:{editorState: EditorState,
                      setEditorState:(editorState: EditorState)=>void}
                      ) {
    const {editorState,setEditorState}= props;

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
                onEditorStateChange={setEditorState}
            />



        </div>
    );
}

export default DetailEditor;