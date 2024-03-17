<template>
  <v-card class="test-content">
    <v-tabs
        v-model="tab"
        centered
        icons-and-text
    >
      <v-tabs-slider></v-tabs-slider>

      <v-tab href="#tab-1">
        Create questions
        <v-icon>mdi-help-box-multiple</v-icon>
      </v-tab>

      <v-tab href="#tab-2">
        Configure test
        <v-icon>mdi-tune</v-icon>
      </v-tab>

      <v-tab href="#tab-3">
        Share
        <v-icon>mdi-account-box</v-icon>
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="tab">
      <v-tab-item :key="1" :value="'tab-1'">
        <TestQuestions/>
      </v-tab-item>

      <v-tab-item :key="2" :value="'tab-2'">
        <TestSettings/>
      </v-tab-item>

      <v-tab-item
          :key="3"
          :value="'tab-3'"
      >
        <v-card flat>
          <v-card-text>{{ 'share test' }}</v-card-text>
        </v-card>
      </v-tab-item>
    </v-tabs-items>

  </v-card>
</template>

<script>

import TestQuestions from "@/components/test/TestQuestions.vue";
import TestSettings from "@/components/test/TestSettings.vue";

export default {
  name: "CreateTest",
  components: {TestQuestions, TestSettings},
  data() {
    return {
      tab: null,
    }
  },
}
</script>

<style scoped>

</style>