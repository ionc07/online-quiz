<template>
  <v-container>
    <v-row class="mt-1">
      <v-col>
        <v-dialog v-model="shareDialog" max-width="500">
          <v-card>
            <v-card-title>
              Share Test Group
              <v-spacer></v-spacer>
              <v-radio-group
                  v-model="shareByOption"
                  row
              >
                <v-radio
                    label="Email"
                    value="email"
                ></v-radio>
                <v-radio
                    label="User group"
                    value="user group"
                ></v-radio>
              </v-radio-group>
            </v-card-title>

            <v-card-text>
              <v-text-field v-if="shareByOption === 'email'" v-model="email" label="User Email"></v-text-field>
              <v-select v-else v-model="userGroupId" item-text="name" item-value="id" :items="userGroups"
                        label="User Group"></v-select>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="shareTestGroup">Share</v-btn>
              <v-btn color="blue darken-1" text @click="shareDialog = false; email='';userGroup=null">Close</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
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

        <v-dialog
            v-model="updateTestGroupDialog"
            persistent
            max-width="600px"
        >
          <v-card>
            <v-card-title>
              <span class="text-h5">Update test group</span>
            </v-card-title>
            <v-card-text>
              <v-row>
                <v-col
                    cols="12"
                >
                  <v-text-field
                      label="Test group name"
                      required
                      v-model="updatedTestGroup.name"
                  ></v-text-field>
                </v-col>

              </v-row>
              <v-row justify="center">
                <v-card-text v-if="updatedTestGroup.tests.length === 0">No tests have been added yet to this test
                  group
                </v-card-text>
                <v-simple-table v-else height="300px">
                  <template v-slot:default>
                    <thead>
                    <tr>
                      <th class="text-left">
                        Test title
                      </th>
                      <th class="text-left">
                        Remove from test group
                      </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr
                        v-for="(test, index) in updatedTestGroup.tests"
                        :key="test.id"
                    >
                      <td>{{ test.title }}</td>
                      <td class="text-right">
                        <v-btn icon color="error" @click="updatedTestGroup.tests.splice(index, 1)">
                          <v-icon color="error">mdi-minus-circle</v-icon>
                        </v-btn>
                      </td>
                    </tr>
                    </tbody>
                  </template>
                </v-simple-table>
              </v-row>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                  color="blue darken-1"
                  text
                  @click="updateTestGroupDialog = false"
              >
                Close
              </v-btn>
              <v-btn
                  color="blue darken-1"
                  text
                  @click="updateTestGroup"
              >
                Save
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

      </v-col>
    </v-row>

    <v-row>
      <v-dialog
          v-model="deleteDialog"
          persistent
          max-width="290"
      >
        <v-card>
          <v-card-title class="text-h6">
            Are you sure?
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
                @click="deleteTestGroup"
            >
              Yes
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-col cols="4" v-for="(testGroupData) in this.data">
        <v-card
            outlined
        >
          <v-list-item three-line>
            <v-list-item-content>
              <v-list-item-title class="text-h6 mb-1">
                {{ testGroupData.name }}
                <v-btn
                    @click=" updatedTestGroup = testGroupData; updateTestGroupDialog = true"
                    icon
                >
                  <v-icon color="black">mdi-square-edit-outline</v-icon>
                </v-btn>
              </v-list-item-title>
              <v-list-item-subtitle class="text">Tests: {{ testGroupData.tests.length }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-card-actions>
            <v-row>
              <v-col>
                <v-btn
                    rounded
                    color="primary"
                    @click="openShareDialog(testGroupData.id)"
                >
                  Share
                  <v-icon color="white">mdi-share</v-icon>
                </v-btn>
              </v-col>
              <v-col cols="auto" align-self="end">
                <v-btn rounded @click="testGroupIdToDelete = testGroupData.id; deleteDialog = true" color="error">
                  <v-icon color="white">mdi-delete</v-icon>
                </v-btn>

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
      shareByOption: 'email',
      sharingTestGroupId: null,
      updatedTestGroup: {tests: []},
      updateTestGroupDialog: null,
      deleteDialog: false,
      createLoading: false,
      newTestGroupName: null,
      testGroupIdToDelete: null,
      createTestGroupDialog: false,
      data: [],
      shareDialog: false,
      email: '',
      userGroup: null,
      userGroupId: null,
      userGroups: [] // Example user groups
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
    fetchUserGroups() {
      this.$store.dispatch("userGroup/getAllUserGroups").then(data => {
        this.userGroups = data;
      })
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
    updateTestGroup() {
      this.$store.dispatch("testGroup/updateTestGroup", this.updatedTestGroup).then(() => {
        this.fetchTestGroups();
      })
      this.updateTestGroupDialog = false;
    },
    openShareDialog(testGroupId) {
      this.sharingTestGroupId = testGroupId;
      this.shareDialog = true;
    },
    shareTestGroup() {
      // Implement logic to share test group with user by email or user group
      console.log('Sharing Test Group');
      console.log('Email:', this.email);
      console.log('User Group:', this.userGroupId);


      // Reset fields and close dialog
      this.email = '';
      this.userGroup = null;
      this.shareDialog = false;
    },
    deleteTestGroup() {
      console.log("delete by id: " + this.testGroupIdToDelete);
      this.$store.dispatch("testGroup/deleteTestGroup", this.testGroupIdToDelete).then(() => {
        this.fetchTestGroups();
      });
      this.deleteDialog = false
    },
  },
  mounted() {
    this.fetchTestGroups();
    this.fetchUserGroups();
  }
};
</script>

<style scoped>
/* Add your custom styles here */
</style>
