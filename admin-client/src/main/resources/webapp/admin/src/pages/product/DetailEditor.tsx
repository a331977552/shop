import React, {useState} from 'react';
import {EditorState, RichUtils} from "draft-js";
import {Editor} from "react-draft-wysiwyg";
import "react-draft-wysiwyg/dist/react-draft-wysiwyg.css";
import htmlToDraft from 'html-to-draftjs';
import draftToHtml from 'draftjs-to-html';


function DetailEditor(props: any) {
    const [editorState, setEditorState] = useState(() => EditorState.createEmpty());
    const editor = React.useRef<any>();


    function onEditorStateChange(editorState: EditorState) {
        console.log(editorState)
    }

    return (
        <div >
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
            /></div>
    );
}

export default DetailEditor;