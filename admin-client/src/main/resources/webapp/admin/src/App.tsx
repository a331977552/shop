import React from 'react';
import './App.css';
import { Layout} from "antd";
import AppHeader from "./components/AppHeader";
import DrawerLeft from "./components/DrawerLeft";
import ContentMain from "./components/ContentMain";
import AppFooter from "./components/AppFooter";



class App extends React.Component {



    render() {
        return (
            <Layout style={{height: '100vh',display:'flex'}}>


                <DrawerLeft/>
                <Layout >
                    <AppHeader id={123}/>
                    <ContentMain />
                    <AppFooter/>

                </Layout>
            </Layout>
        );
    }

}


export default App;
