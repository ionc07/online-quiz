import Vue from 'vue';
import Vuex from 'vuex';

import {auth} from './modules/auth.module';
import {nav} from "./modules/nav.module";
import {test} from "@/store/modules/test.module";
import {testGroup} from "@/store/modules/testGroups.module";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    nav,
    test,
    testGroup
  }
});