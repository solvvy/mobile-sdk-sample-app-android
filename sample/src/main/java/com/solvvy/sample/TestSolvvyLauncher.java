package com.solvvy.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.solvvy.sdk.config.SolvvySdk;
import com.solvvy.sdk.model.ChatSupportOption;
import com.solvvy.sdk.model.CommunitySupportOption;
import com.solvvy.sdk.model.EmailSupportOption;
import com.solvvy.sdk.model.PhoneSupportOption;
import com.solvvy.sdk.model.SupportOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Launcher to test Configurations
 */
public class TestSolvvyLauncher extends AppCompatActivity {

    SolvvySdk.SolvvySdkCallBack solvvySdkCallBack = new SolvvySdk.SolvvySdkCallBack() {
        @Override
        public List<SupportOption> getSupportOption(Map<String, String> solvvyState) {
            List<SupportOption> supportOptions = new ArrayList<>();
            supportOptions.add(new ChatSupportOption());
            supportOptions.add(new EmailSupportOption());
            CommunitySupportOption communitySupportOption = new CommunitySupportOption();
            communitySupportOption.setCommunityLink("https://community.upwork.com/");
            supportOptions.add(communitySupportOption);
            PhoneSupportOption phoneSupportOption = new PhoneSupportOption();
            phoneSupportOption.setPhoneNo("+917353980930");
            supportOptions.add(phoneSupportOption);

            return supportOptions;
        }

        @Override
        public void handleChatOption(final SupportOption supportOption,
                                     final FragmentActivity context,
                                     Map<String, String> solvvyState) {
            //Implement
        }

        @Override
        @SuppressWarnings("squid:CommentedOutCodeLine")
        public void handleCallOption(SupportOption supportOption, FragmentActivity context,
                                     Map<String, String> solvvyState) {
            super.handleCallOption(supportOption, context, solvvyState);
            // discard the default implementation by not calling super.handleCallOption
            // (supportOption, context);
        }

        @Override
        @SuppressWarnings("squid:CommentedOutCodeLine")
        public void handleCommunityOption(SupportOption supportOption, FragmentActivity context,
                                          Map<String, String> solvvyState) {
            super.handleCommunityOption(supportOption, context, solvvyState);
            // discard the default implementation by not calling super.handleCommunityOption
            // (supportOption, context);
        }

        @Override
        public void handleCustomOption(SupportOption supportOption,
                                       FragmentActivity context,
                                       Map<String, String> solvvyState) {
            super.handleCustomOption(supportOption, context, solvvyState);
            // Called when user taps on the custom support option
        }

        @Override
        public boolean showQuestionSearch(final Map<String, String> solvvyStates) {
            return true;
        }
    };
    private SolvvySdk solvvySdkInstance;

    @Override
    @SuppressWarnings("squid:S1192")
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_solvvy_launcher);

        findViewById(R.id.sense).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();
                SolvvySdk.Persona.Builder config = new SolvvySdk.Persona.Builder();
                config.apiKey("a638e987-cef8-4f41-91be-d46d0f0ae8b9~6bbn06VQf6Tf9CP202opSWRyGgvaI6oKq7IudmHSwK8ofupq8MNApqnFqq58pXul")
                        .connectorIdForTicketCreation("08c169c7-5079-4787-99ea-0ad2941bf219")
                        .orgId("314");
                solvvySdkInstance.init(config.build());
                launchTestSolvvy();

            }
        });

        findViewById(R.id.orchesrated_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();
                SolvvySdk.Persona.Builder config = new SolvvySdk.Persona.Builder();
                config.apiKey(
                        "f251d92a-516b-44eb-916c-c1f022696c3f"
                                +
                                "~9M4yWl9W8xsgxjM23TK1Fm31hAS906NYijGmmS8XKD1km1o34s2LB6Ud8eKyCx57")
                        .connectorIdForTicketCreation("d99d93c6-a021-47ca-ab19-d7e9b258f32d")
                        .orgId("116");

                SolvvySdk.FormSettings.Builder commonOptionBuilder =
                        new SolvvySdk.FormSettings.Builder();
                SolvvySdk.FormSettings.PreQuestionForm preQuest =
                        new SolvvySdk.FormSettings.PreQuestionForm();
                preQuest.setShow(false);
                SolvvySdk.FormSettings.PreContactForm preFrom =
                        new SolvvySdk.FormSettings.PreContactForm();
                preFrom.setShow(true);

                Map<String, String> initialContext = new HashMap<>();
                initialContext.put("custom_303132", "test subject");
                initialContext.put("custom_23028966", "administration__it_related__zendesk_users");

                List<String> hideList = new ArrayList<>(2);
                hideList.add("custom_303132");
                hideList.add("custom_23028966");

                commonOptionBuilder.preContactForm(preFrom)
                        .preQuestionForm(preQuest)
                        .allowAttachments(true)
                        .requireCaptcha(false)
                        .solvvyState(initialContext)
                        .hidePropertyList(hideList)
                        .userSelectsForm(true);
                solvvySdkInstance.setSolvvySdkCallback(solvvySdkCallBack);
                solvvySdkInstance.init(config.build());
                solvvySdkInstance.setSupportEmailId("support@solvvy.com");
                solvvySdkInstance.setFormSettings(commonOptionBuilder.build());
                launchTestSolvvy();
            }
        });

        findViewById(R.id.up_work).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();
                SolvvySdk.Persona.Builder upworkConfig = new SolvvySdk.Persona.Builder();
                upworkConfig.apiKey("880e73f9-9ca8-4ee3-9abb-e2369b11ec1f"
                        + "~o4Qj1sO9muoswswkKCS00Kk4g08G8K8Fu7woqsA3xkoGWgc8cWww48kg8OSwCs")
                        .orgId("1")
                        .connectorIdForTicketCreation("db9b9928-7dfa-42bd-b4bf-7c202e6456c0");
                String[] preContactFieldWhiteList = {"custom_44456188",
                        "custom_44519307",
                        "custom_44523707",
                        "custom_44525407",
                        "custom_44457808",
                        "custom_44459308",
                        "custom_44460968",
                        "custom_44463528",
                        "custom_44464668",
                        "custom_44534007"
                };
                solvvySdkInstance.setSolvvySdkCallback(solvvySdkCallBack);
                SolvvySdk.FormSettings.Builder formSettings = new SolvvySdk.FormSettings.Builder();
                SolvvySdk.FormSettings.PreQuestionForm preQuest =
                        new SolvvySdk.FormSettings.PreQuestionForm();
                preQuest.setShow(false);
                SolvvySdk.FormSettings.PreContactForm preFrom =
                        new SolvvySdk.FormSettings.PreContactForm();
                preFrom.setShow(true);
                preFrom.setFieldIdWhitelist(Arrays.asList(preContactFieldWhiteList));

                Map<String, String> initialContext = new HashMap<>();
                initialContext.put("email", "test@gmail.com");

                formSettings.preContactForm(preFrom)
                        .preQuestionForm(preQuest)
                        .allowAttachments(true)
                        .solvvyState(initialContext)
                        .userSelectsForm(true);
                solvvySdkInstance.init(upworkConfig.build());
                solvvySdkInstance.setFormSettings(formSettings.build());
                launchTestSolvvy();
            }
        });
        findViewById(R.id.task_rabbit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();
                List<SolvvySdk.Persona> personaParams = new ArrayList<>(2);
                SolvvySdk.Persona.Builder studentConfig = new SolvvySdk.Persona.Builder();
                studentConfig.apiKey(
                        "2da494c7-eea3-4ccd-9254-69c74e47e38c"
                                +
                                "~fXH4xxwchGLjYiQ2j7BxIA6pdjOQ323Kt1Py0b9Q30cK04b5fr5h9C3M4o1w8TK1")
                        .connectorIdForTicketCreation("e5ebd78b-3d4f-4694-a106-6af579d5604b")
                        .orgId("21")
                        .buttonText("Client");
                personaParams.add(studentConfig.build());

                SolvvySdk.Persona.Builder instructorConfig = new SolvvySdk.Persona.Builder();
                instructorConfig.apiKey(
                        "ac0eaba3-afcb-4b90-af34-b2bb67b169b6"
                                +
                                "~dgly6qEfuLdf298sTncktfwqoef310E2uL8MR911IquY07Dph9527BHlyNGRuIKu")
                        .connectorIdForTicketCreation("a9577b3e-0b2e-4089-84de-035fd4a340ba")
                        .orgId("87")
                        .buttonText("Tasker");
                personaParams.add(instructorConfig.build());

                SolvvySdk.Persona.Builder registeringConfig = new SolvvySdk.Persona.Builder();
                registeringConfig.apiKey(
                        "d98b9959-18d0-4556-a362-24beaa7a2c77"
                                +
                                "~kpXW73cgwhj2CcUKUcriQjTRki3jpO59NE6pF4wU3a4Y87Lc8LjZoXfUbXVcQVgx")
                        .connectorIdForTicketCreation("336ce797-1619-4643-8597-652bccea4927")
                        .orgId("86")
                        .buttonText("Registering");
                personaParams.add(registeringConfig.build());


                SolvvySdk.FormSettings.Builder commonOptionBuilder =
                        new SolvvySdk.FormSettings.Builder();
                SolvvySdk.FormSettings.PreQuestionForm preQuest =
                        new SolvvySdk.FormSettings.PreQuestionForm();
                preQuest.setShow(false);
                SolvvySdk.FormSettings.PreContactForm preFrom =
                        new SolvvySdk.FormSettings.PreContactForm();
                preFrom.setShow(true);
                commonOptionBuilder.preContactForm(preFrom)
                        .preQuestionForm(preQuest)
                        .allowAttachments(true)
                        .userSelectsForm(false);
                solvvySdkInstance.init(personaParams);
                solvvySdkInstance.setFormSettings(commonOptionBuilder.build());
                launchTestSolvvy();
            }
        });
        findViewById(R.id.head_space).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();
                SolvvySdk.Persona.Builder config = new SolvvySdk.Persona.Builder();
                config.apiKey(
                        "0e66f887-7a15-4c4d-9bda-a6b2b8adb1ff"
                                +
                                "~3Hlbdb92RXd8x4G22G0VfVPxd99g7av73pLDqwhxkC0Q55UDU6RnLnyEAPffGnR2")
                        .connectorIdForTicketCreation("07aa5a11-7133-4af4-affc-f35af3362426")
                        .orgId("54");

                SolvvySdk.FormSettings.Builder commonOptionBuilder =
                        new SolvvySdk.FormSettings.Builder();
                SolvvySdk.FormSettings.PreQuestionForm preQuest =
                        new SolvvySdk.FormSettings.PreQuestionForm();
                preQuest.setShow(false);
                SolvvySdk.FormSettings.PreContactForm preFrom =
                        new SolvvySdk.FormSettings.PreContactForm();
                preFrom.setShow(true);
                commonOptionBuilder.preContactForm(preFrom)
                        .preQuestionForm(preQuest)
                        .allowAttachments(true)
                        .userSelectsForm(true);
                solvvySdkInstance.init(config.build());
                solvvySdkInstance.setFormSettings(commonOptionBuilder.build());
                launchTestSolvvy();
            }
        });

        findViewById(R.id.zwift).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSolvvy();
                SolvvySdk.Persona.Builder config = new SolvvySdk.Persona.Builder();
                config.apiKey(
                        "b783daee-822e-4fb8-a30e-156a90f838c8"
                                +
                                "~7XinBT93Xh4gp1BqFF9haKU6RmZS2QxS39IeqNP181l6Y3yV4EIc40LD95qV1ke4")
                        .connectorIdForTicketCreation("edf6577d-8ef1-478c-8a7a-b00dfe24284f")
                        .orgId("184");
                SolvvySdk.FormSettings.Builder commonOptionBuilder =
                        new SolvvySdk.FormSettings.Builder();
                SolvvySdk.FormSettings.PreQuestionForm preQuest =
                        new SolvvySdk.FormSettings.PreQuestionForm();
                preQuest.setShow(false);
                SolvvySdk.FormSettings.PreContactForm preFrom =
                        new SolvvySdk.FormSettings.PreContactForm();
                preFrom.setShow(false);
                solvvySdkInstance.init(config.build());
                solvvySdkInstance.setSolvvySdkCallback(solvvySdkCallBack);
                commonOptionBuilder.preContactForm(preFrom)
                        .preQuestionForm(preQuest)
                        .allowAttachments(true)
                        .userSelectsForm(true);
                solvvySdkInstance.setFormSettings(commonOptionBuilder.build());
                launchTestSolvvy();

            }
        });


        // Handle Heads Space test cases


        findViewById(R.id.simplestIntegration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();
                buildBasicConfig();
                launchTestSolvvy();

            }
        });

        findViewById(R.id.onlyPreQuestionForm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();
                buildBasicConfig();

                SolvvySdk.FormSettings.Builder commonOptionBuilder =
                        new SolvvySdk.FormSettings.Builder();
                SolvvySdk.FormSettings.PreQuestionForm preQuest =
                        new SolvvySdk.FormSettings.PreQuestionForm();
                preQuest.setShow(true);
                commonOptionBuilder
                        .preQuestionForm(preQuest);
                solvvySdkInstance.setFormSettings(commonOptionBuilder.build());

                launchTestSolvvy();

            }
        });


        findViewById(R.id.bothPreQuestionAndPreContact).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initSolvvy();
                        buildBasicConfig();
                        SolvvySdk.FormSettings.Builder commonOptionBuilder =
                                new SolvvySdk.FormSettings.Builder();

                        SolvvySdk.FormSettings.PreQuestionForm preQuest =
                                new SolvvySdk.FormSettings.PreQuestionForm();
                        preQuest.setShow(true);
                        SolvvySdk.FormSettings.PreContactForm preContactForm =
                                new SolvvySdk.FormSettings.PreContactForm();
                        preContactForm.setShow(true);
                        commonOptionBuilder
                                .preQuestionForm(preQuest)
                                .preContactForm(preContactForm);
                        solvvySdkInstance.setSolvvySdkCallback(new SolvvySdk.SolvvySdkCallBack() {
                            @Override
                            public List<SupportOption> getSupportOption(
                                    Map<String, String> solvvyState) {
                                List<SupportOption> supportOptions = new ArrayList<>();
                                supportOptions.add(new EmailSupportOption());
                                return supportOptions;
                            }

                        });
                        solvvySdkInstance.setFormSettings(commonOptionBuilder.build());
                        launchTestSolvvy();

                    }
                });
        findViewById(R.id.enableChatAndEmailSupportOptions).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initSolvvy();
                        buildBasicConfig();
                        solvvySdkInstance.setSolvvySdkCallback(new SolvvySdk.SolvvySdkCallBack() {
                            @Override
                            public List<SupportOption> getSupportOption(
                                    Map<String, String> solvvyState) {
                                List<SupportOption> supportOptions = new ArrayList<>();
                                supportOptions.add(new ChatSupportOption());
                                supportOptions.add(new EmailSupportOption());
                                return supportOptions;
                            }
                        });
                        launchTestSolvvy();

                    }
                });

        findViewById(R.id.enableCustomSupportOption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();
                buildBasicConfig();
                solvvySdkInstance.setSolvvySdkCallback(new SolvvySdk.SolvvySdkCallBack() {
                    @Override
                    public List<SupportOption> getSupportOption(Map<String, String> solvvyState) {
                        List<SupportOption> supportOptions = new ArrayList<>();
                        CustomSupportOption customSupportOption = new CustomSupportOption();
                        customSupportOption.setTitle("Custom");
                        customSupportOption.setDescription("Custom description");
                        customSupportOption.setButtonDescription("Custom button text");
                        customSupportOption.setTitleImageResource(R.drawable.ic_custom_support);
                        supportOptions.add(customSupportOption);
                        return supportOptions;
                    }
                });
                launchTestSolvvy();

            }
        });

        findViewById(R.id.showQuestionFormFalse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();
                buildBasicConfig();
                solvvySdkInstance.setSolvvySdkCallback(new SolvvySdk.SolvvySdkCallBack() {
                    @Override
                    public boolean showQuestionSearch(Map<String, String> solvvyState) {
                        return false;
                    }
                });
                launchTestSolvvy();

            }
        });

        findViewById(R.id.allowAttachmentsAndCaptcha).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initSolvvy();
                        buildBasicConfig();
                        SolvvySdk.FormSettings.Builder commonOptionBuilder =
                                new SolvvySdk.FormSettings.Builder();
                        commonOptionBuilder.allowAttachments(true)
                                .requireCaptcha(true);
                        solvvySdkInstance.setFormSettings(commonOptionBuilder.build());
                        launchTestSolvvy();
                    }
                });

        findViewById(R.id.customticketid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();
                buildBasicConfig();
                SolvvySdk.FormSettings.Builder commonOptionBuilder =
                        new SolvvySdk.FormSettings.Builder();
                commonOptionBuilder.customTicketFormId("360000064474");
                solvvySdkInstance.setFormSettings(commonOptionBuilder.build());
                launchTestSolvvy();

            }
        });

        findViewById(R.id.singlePhone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();
                buildBasicConfig();
                launchTestSolvvy();
                solvvySdkInstance.setSolvvySdkCallback(new SolvvySdk.SolvvySdkCallBack() {
                    @Override
                    public List<SupportOption> getSupportOption(Map<String, String> solvvyState) {
                        List<SupportOption> supportOptions = new ArrayList<>();
                        final PhoneSupportOption phoneSupportOption = new PhoneSupportOption();
                        phoneSupportOption.setPhoneNo("+911234567890");
                        supportOptions.add(phoneSupportOption);
                        return supportOptions;
                    }
                });

            }
        });

        findViewById(R.id.singleEmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();
                buildBasicConfig();
                launchTestSolvvy();
                solvvySdkInstance.setSolvvySdkCallback(new SolvvySdk.SolvvySdkCallBack() {
                    @Override
                    public List<SupportOption> getSupportOption(Map<String, String> solvvyState) {
                        List<SupportOption> supportOptions = new ArrayList<>();
                        final EmailSupportOption supportOption = new EmailSupportOption();
                        supportOptions.add(supportOption);
                        return supportOptions;
                    }
                });

            }
        });

        findViewById(R.id.singleCommunity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();
                buildBasicConfig();
                launchTestSolvvy();
                solvvySdkInstance.setSolvvySdkCallback(new SolvvySdk.SolvvySdkCallBack() {
                    @Override
                    public List<SupportOption> getSupportOption(Map<String, String> solvvyState) {
                        List<SupportOption> supportOptions = new ArrayList<>();
                        final CommunitySupportOption supportOption = new CommunitySupportOption();
                        supportOption.setCommunityLink("https://google.com");
                        supportOptions.add(supportOption);
                        return supportOptions;
                    }
                });

            }
        });

        findViewById(R.id.singleChatOption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();
                buildBasicConfig();
                launchTestSolvvy();
                solvvySdkInstance.setSolvvySdkCallback(new SolvvySdk.SolvvySdkCallBack() {
                    @Override
                    public List<SupportOption> getSupportOption(Map<String, String> solvvyState) {
                        List<SupportOption> supportOptions = new ArrayList<>();
                        final ChatSupportOption supportOption = new ChatSupportOption();
                        supportOptions.add(supportOption);
                        return supportOptions;
                    }

                    @Override
                    public void handleChatOption(SupportOption supportOption,
                                                 FragmentActivity context, Map<String, String> solvvyState) {
                        super.handleChatOption(supportOption, context, solvvyState);
                        Toast.makeText(getBaseContext(), "Chat option clicked", Toast.LENGTH_SHORT).show();
                        solvvySdkInstance.stopSolvvy();
                    }
                });

            }
        });

    }

    private void buildBasicConfig() {
        SolvvySdk.Persona.Builder config = new SolvvySdk.Persona.Builder();
        config.apiKey(
                "0e66f887-7a15-4c4d-9bda-a6b2b8adb1ff"
                        +
                        "~3Hlbdb92RXd8x4G22G0VfVPxd99g7av73pLDqwhxkC0Q55UDU6RnLnyEAPffGnR2")
                .connectorIdForTicketCreation("07aa5a11-7133-4af4-affc-f35af3362426")
                .orgId("54");
        solvvySdkInstance.init(config.build());
    }

    private void initSolvvy() {
        SolvvySdk.clear();
        solvvySdkInstance = SolvvySdk.getInstance();
    }

    private void launchTestSolvvy() {
        solvvySdkInstance.startSolvvy(this);
    }

}
