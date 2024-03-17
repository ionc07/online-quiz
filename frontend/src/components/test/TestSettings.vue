<template>
  <v-container>
    <v-row>
      <v-col cols="12" sm="6">
        <v-card>
          <v-card-title>Settings</v-card-title>
          <v-card-text>
            <v-row>
              <v-col cols="3">
                <v-menu
                    v-model="availableFromDateMenu"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    transition="scale-transition"
                    offset-y
                    min-width="auto"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                        v-model="date"
                        label="Available from date"
                        prepend-icon="mdi-calendar"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                      v-model="date"
                      @input="availableFromDateMenu = false"
                  ></v-date-picker>
                </v-menu>
              </v-col>
              <v-col cols="3">
                <v-menu
                    ref="menu"
                    v-model="availableFromTimeMenu"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    :return-value.sync="time"
                    transition="scale-transition"
                    offset-y
                    max-width="290px"
                    min-width="290px"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                        v-model="availableFromTime"
                        label="Available from time"
                        prepend-icon="mdi-clock-time-four-outline"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                      v-if="availableFromTimeMenu"
                      v-model="availableFromTime"
                      full-width
                      @click:minute="$refs.menu.save(time)"
                  ></v-time-picker>
                </v-menu>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="3">
                <v-checkbox v-model="chatEnabled" label="Chat Enabled"></v-checkbox>
              </v-col>
              <v-col cols="12">
                <v-text-field v-model="timeLimit" label="Time Limit" type="number"></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field v-model="maxAttempts" label="Max Attempts" type="number"></v-text-field>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      availableFrom: null,
      availableFromTime: null,
      availableTo: null,
      chatEnabled: false,
      timeLimit: null,
      maxAttempts: null,
      fromPicker: false,
      toPicker: false,
      date: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      menu: false,
      modal: false,
      availableFromDateMenu: false,
      availableFromTimeMenu: false,
    };
  },
  computed: {
    formattedAvailableFrom() {
      return this.availableFrom ? this.formatDateTime(this.availableFrom) : '';
    },
    formattedAvailableTo() {
      return this.availableTo ? this.formatDateTime(this.availableTo) : '';
    }
  },
  methods: {
    openFromPicker() {
      this.fromPicker = true;
    },
    openToPicker() {
      this.toPicker = true;
    },
    updateFormattedTime() {
      // Update formatted time if needed
    },
    formatDateTime(dateTime) {
      console.log(dateTime);
      // Format dateTime as needed
    }
  }
};
</script>
