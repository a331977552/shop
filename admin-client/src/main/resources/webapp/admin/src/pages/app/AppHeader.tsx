import React from 'react';
import styled from 'styled-components'
import {Layout} from "antd";

import {useAppSelector} from "../../store/hooks";
import {selectUser} from "../../store/slices/userSlice";
import {UserModel} from '../../model';
const {Header} = Layout;

const StyledSpan = styled.span`
  cursor: pointer;
  align-self: center;
  height: 30px;
  line-height: 30px;
  text-decoration: underline;
  color: white;
`

const AppHeader = (props:any) => {
    const user = useAppSelector(selectUser)?.result;
    return (
        <Header style={{display: 'flex',height:'50px', padding: '0 30px'}}>
            <div style={{backgroundColor: 'white', width: 200, height: 34, alignSelf: "center"}}/>
            <div style={{flex: '1 0 0px'}}/>
            <div style={{display: 'flex'}}>
                 <StyledSpan > {user?.alias}</StyledSpan>
            </div>
        </Header>
    );
};
export default AppHeader;

//
// const mapStateToProps = (state: RootState) => ({
//
// })
//
// const mapDispatch = {
//     toggleOn: () => ({ type: 'TOGGLE_IS_ON' }),
// }
//
//
// const connector = connect(mapStateToProps, mapDispatch)
//
// // The inferred type will look like:
// // {isOn: boolean, toggleOn: () => void}
// type PropsFromRedux = ConnectedProps<typeof connector>
//
// interface Props extends PropsFromRedux{
//     id:number
// }

