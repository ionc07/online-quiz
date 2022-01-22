<template>
  <v-app>
    <v-container>
      <v-row align="center" justify="center" class="mt-12">
        <v-col
            cols="12"
            sm="6"
            md="4"
            align="center" justify="center"
        >
          <v-img
              max-width="20%"
              src="@/assets/quiz-logo.png"
          ></v-img>
        </v-col>
      </v-row>
      <Login @switchActionType="switchActionType" v-if="actionType === 'Login'" v-bind:action-type="actionType"/>
      <SignUp @switchActionType="switchActionType" v-if="actionType === 'SignUp'"/>
    </v-container>
  </v-app>
</template>

<script>
import Login from "@/components/Login";
import SignUp from "@/components/SignUp";

export default {
  name: "Authentication",
  components: {SignUp, Login},
  data() {
    return {
      actionType: "Login"
    }
  },
  methods: {
    switchActionType(actionType) {
      this.actionType = actionType;
    }
  }
}
</script>

<style scoped>

</style>