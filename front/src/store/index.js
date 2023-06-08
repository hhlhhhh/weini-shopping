import {createStore} from "vuex";
import user from "./user";
import order from './order'


export default createStore({
    modules:{
        user,
        order
    }
})