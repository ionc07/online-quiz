<template>
  <v-container>
    <v-row class="mt-1">
      <v-col>


        <v-dialog
            v-model="createTestGroupDialog"
            persistent
            max-width="600px"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="primary"
                   v-bind="attrs"
                   v-on="on"
            >
              Create
              <v-icon dense right color="white">mdi-plus</v-icon>
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="text-h5">Create new test group</span>
            </v-card-title>
            <v-card-text>
              <v-row>
                <v-col
                    cols="12"
                >
                  <v-text-field
                      label="Test group name"
                      required
                      v-model="newTestGroupName"
                  ></v-text-field>
                </v-col>

              </v-row>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                  color="blue darken-1"
                  text
                  @click="createTestGroupDialog = false"
              >
                Close
              </v-btn>
              <v-btn
                  color="blue darken-1"
                  text
                  @click="createTestGroup()"
              >
                Save
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="4" v-for="(testGroupData) in this.data">
        <v-card
            outlined
        >
          <v-list-item three-line>
            <v-list-item-content>
              <v-list-item-title class="text-h6 mb-1">
                {{ testGroupData.name }}
                <v-btn
                    icon
                >
                  <v-icon color="black">mdi-square-edit-outline</v-icon>
                </v-btn>
              </v-list-item-title>
              <v-list-item-subtitle class="text">Tests: {{ testGroupData.testsCount }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-card-actions>
            <v-row>
              <v-col>
                <v-btn
                    rounded
                    color="primary"
                >
                  Share
                  <v-icon color="white">mdi-share</v-icon>
                </v-btn>
              </v-col>
              <v-col cols="auto" align-self="end">
                <v-dialog
                    v-model="deleteDialog"
                    persistent
                    max-width="290"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                        v-bind="attrs" v-on="on"
                        rounded
                        color="error"
                        @click="deleteDialog = false"
                    >
                      <v-icon color="white">mdi-delete</v-icon>
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title class="text-h6">
                      Are you sure you want to delete the selected tests?
                    </v-card-title>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn
                          color="red"
                          text
                          @click="deleteDialog = false"
                      >
                        Cancel
                      </v-btn>
                      <v-btn
                          color="error"
                          @click="deleteTestGroup(testGroupData.id)"
                      >
                        Yes
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>

              </v-col>
            </v-row>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      deleteDialog: false,
      createLoading: false,
      newTestGroupName: null,
      createTestGroupDialog: false,
      data: [],
    };
  },
  methods: {
    fetchTestGroups() {
      console.log(this.$store)
      this.$store.dispatch("testGroup/getAllTestGroups").then(data => {
        console.log(data);
        this.data = data;
        this.loading = false
      });
    },
    createTestGroup() {
      console.log(this.$store)
      this.$store.dispatch("testGroup/createTestGroup", {name: this.newTestGroupName}).then(data => {
        console.log(data);
        this.createTestGroupDialog = false;
        this.createLoading = false;
        this.newTestGroupName = null;
        this.fetchTestGroups();
      })
    },
    renameGroup(id) {
      // Implement renaming group functionality
    },
    deleteTestGroup(id) {
      this.$store.dispatch("testGroup/deleteTestGroup", id);
      this.fetchTestGroups();
    },
  },
  mounted() {
    this.fetchTestGroups();
  }
};
</script>

<style scoped>
/* Add your custom styles here */
</style>
