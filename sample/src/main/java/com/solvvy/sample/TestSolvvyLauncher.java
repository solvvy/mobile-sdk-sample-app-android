package com.solvvy.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.solvvy.sdk.config.SolvvySdk;
import com.solvvy.sdk.model.ChatSupportOption;
import com.solvvy.sdk.model.CommunitySupportOption;
import com.solvvy.sdk.model.EmailSupportOption;
import com.solvvy.sdk.model.PhoneSupportOption;
import com.solvvy.sdk.model.SupportOption;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Launcher to test Configurations
 */
public class TestSolvvyLauncher extends AppCompatActivity {

    SolvvySdk.SolvvySdkCallBack solvvySdkCallBack = new SolvvySdk.SolvvySdkCallBack() {
        @Override
        public List<SupportOption> getSupportOption(Map<String, Object> solvvyState) {
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
                                     Map<String, Object> solvvyState) {
            //Implement
        }

        @Override
        @SuppressWarnings("squid:CommentedOutCodeLine")
        public void handleCallOption(SupportOption supportOption, FragmentActivity context,
                                     Map<String, Object> solvvyState) {
            super.handleCallOption(supportOption, context, solvvyState);
            // discard the default implementation by not calling super.handleCallOption
            // (supportOption, context);
        }

        @Override
        @SuppressWarnings("squid:CommentedOutCodeLine")
        public void handleCommunityOption(SupportOption supportOption, FragmentActivity context,
                                          Map<String, Object> solvvyState) {
            super.handleCommunityOption(supportOption, context, solvvyState);
            // discard the default implementation by not calling super.handleCommunityOption
            // (supportOption, context);
        }

        @Override
        public void handleCustomOption(SupportOption supportOption,
                                       FragmentActivity context,
                                       Map<String, Object> solvvyState) {
            super.handleCustomOption(supportOption, context, solvvyState);
            // Called when user taps on the custom support option
        }

        @Override
        public boolean showQuestionSearch(final Map<String, Object> solvvyStates) {
            return true;
        }
    };
    private SolvvySdk solvvySdkInstance;

    private SolvvySdk.Persona getSolvyPersona() {
        return new SolvvySdk.Persona.Builder()
                .apiKey("5dc04bb4-6bbd-4b4e-a1e5-54a6b91ab4e1~7l0915aPyx8Oc8Osss0s00CCW8Kg44w5jIIELyt23wOk4KGCKCgO80o0o0040s")
                .connectorIdForTicketCreation("d3bdb21e-451b-4267-871e-e6f79a37dc87")
                .orgId("2").build();
    }

    private SolvvySdk.Persona getOnXPersona() {
        return new SolvvySdk.Persona.Builder()
                .apiKey("a68dd9cd-db88-478b-808b-9304035bae6d~8329Qaf826wT5Lq5iPLXgoHQ3AJ84o1zbEp4SD0hd5k1i9PTnvT6ivVo81I7SvSF")
                .connectorIdForTicketCreation("278e8850-0b6c-493b-9d8e-29db6c0a6ce2")
                .orgId("352").build();
    }

    @Override
    @SuppressWarnings("squid:S1192")
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_solvvy_launcher);

        findViewById(R.id.defaultFormOption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();

                SolvvySdk.FormSettings.Builder commonOptionBuilder =
                        new SolvvySdk.FormSettings.Builder();
                SolvvySdk.FormSettings.PreQuestionForm preQuest =
                        new SolvvySdk.FormSettings.PreQuestionForm();
                preQuest.setShow(false);

                SolvvySdk.FormSettings.PreContactForm preContactForm =
                        new SolvvySdk.FormSettings.PreContactForm();
                preContactForm.setShow(true);
                commonOptionBuilder
                        .preQuestionForm(preQuest)
                        .preContactForm(preContactForm)
                        .allowAttachments(true)
                        .requireCaptcha(false);
                solvvySdkInstance.setSolvvySdkCallback(solvvySdkCallBack);
                solvvySdkInstance.init(getSolvyPersona());
                solvvySdkInstance.setSupportEmailId("support@solvvy.com");
                solvvySdkInstance.setFormSettings(commonOptionBuilder.build());
                launchTestSolvvy();

            }
        });

        findViewById(R.id.SpecificFormOption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();

                SolvvySdk.FormSettings.Builder commonOptionBuilder =
                        new SolvvySdk.FormSettings.Builder();
                SolvvySdk.FormSettings.PreQuestionForm preQuest =
                        new SolvvySdk.FormSettings.PreQuestionForm();
                preQuest.setShow(false);

                SolvvySdk.FormSettings.PreContactForm preContactForm =
                        new SolvvySdk.FormSettings.PreContactForm();
                preContactForm.setShow(false);

                Map<String, Object> state = new HashMap<>();
                state.put("email",  "sa@sa.com");
                //dropdown
                state.put("custom_360000009888",  "WOLF");
                //date
                state.put("custom_81267848", Calendar.getInstance());
                state.put("custom_80879687",  true);


                commonOptionBuilder
                        .preQuestionForm(preQuest)
                        .preContactForm(preContactForm)
                        .allowAttachments(true)
                        .requireCaptcha(false)
                        .customTicketFormId("863608")
                        .solvvyState(state)
                        .userSelectsForm(false);
                solvvySdkInstance.setSolvvySdkCallback(solvvySdkCallBack);
                solvvySdkInstance.init(getSolvyPersona());
                solvvySdkInstance.setSupportEmailId("support@solvvy.com");
                solvvySdkInstance.setFormSettings(commonOptionBuilder.build());
                launchTestSolvvy();
            }
        });
        findViewById(R.id.SpecificFormSelectedAndUserFormSelectionOption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();

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
                        .preContactForm(preContactForm)
                        .allowAttachments(true)
                        .requireCaptcha(false)
                        .customTicketFormId("774248")
                        .userSelectsForm(true);
                solvvySdkInstance.setSolvvySdkCallback(solvvySdkCallBack);
                solvvySdkInstance.init(getSolvyPersona());
                solvvySdkInstance.setSupportEmailId("support@solvvy.com");
                solvvySdkInstance.setFormSettings(commonOptionBuilder.build());
                launchTestSolvvy();
            }
        });

        findViewById(R.id.UserFormSelectionOption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();

                SolvvySdk.FormSettings.Builder commonOptionBuilder =
                        new SolvvySdk.FormSettings.Builder();
                SolvvySdk.FormSettings.PreQuestionForm preQuest =
                        new SolvvySdk.FormSettings.PreQuestionForm();
                preQuest.setShow(false);

                SolvvySdk.FormSettings.PreContactForm preContactForm =
                        new SolvvySdk.FormSettings.PreContactForm();
                preContactForm.setShow(false);


                Map<String, Object> state = new HashMap<>();

                state.put("email",  "sa@sa.com");
                //dropdown
                state.put("custom_360000009888",  "WOLF");
                //date
                state.put("custom_81267848", Calendar.getInstance());
                state.put("custom_80879687",  true);
                commonOptionBuilder
                        //.preQuestionForm(preQuest)
                        .allowAttachments(true)
                        .requireCaptcha(false)
                        .solvvyState(state)
                        .userSelectsForm(true);
                solvvySdkInstance.setSolvvySdkCallback(solvvySdkCallBack);
                solvvySdkInstance.init(getSolvyPersona());
                solvvySdkInstance.setSupportEmailId("support@solvvy.com");
                solvvySdkInstance.setFormSettings(commonOptionBuilder.build());
                launchTestSolvvy();
            }
        });

        findViewById(R.id.OnXForm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSolvvy();

                SolvvySdk.FormSettings.Builder commonOptionBuilder =
                        new SolvvySdk.FormSettings.Builder();
                Map<String, Object> state = new HashMap<>();
                state.put("email",  "sa@sa.com");

                //state.put("custom_33989767",  "App FOR Android (Phone or Tablet)");
                state.put("custom_33989767",  "app_android");
                commonOptionBuilder
                        .allowAttachments(true)
                        .requireCaptcha(false)
                        .solvvyState(state)
                        .customTicketFormId("252998");
                solvvySdkInstance.setSolvvySdkCallback(solvvySdkCallBack);
                solvvySdkInstance.init(getOnXPersona());
                //solvvySdkInstance.setSupportEmailId("support@solvvy.com");
                solvvySdkInstance.setFormSettings(commonOptionBuilder.build());
                launchTestSolvvy();
            }
        });
        /*findViewById(R.id.task_rabbit).setOnClickListener(new View.OnClickListener() {
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
        });*/
        /*findViewById(R.id.head_space).setOnClickListener(new View.OnClickListener() {
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
        });*/

       /* findViewById(R.id.zwift).setOnClickListener(new View.OnClickListener() {
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
        });*/


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
                        .preQuestionForm(preQuest)
                        .userSelectsForm(true);
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
                                    Map<String, Object> solvvyState) {
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
                                    Map<String, Object> solvvyState) {
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
                    public List<SupportOption> getSupportOption(Map<String, Object> solvvyState) {
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
                    public boolean showQuestionSearch(Map<String, Object> solvvyState) {
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
                    public List<SupportOption> getSupportOption(Map<String, Object> solvvyState) {
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
                    public List<SupportOption> getSupportOption(Map<String, Object> solvvyState) {
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
                    public List<SupportOption> getSupportOption(Map<String, Object> solvvyState) {
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
                    public List<SupportOption> getSupportOption(Map<String, Object> solvvyState) {
                        List<SupportOption> supportOptions = new ArrayList<>();
                        final ChatSupportOption supportOption = new ChatSupportOption();
                        supportOptions.add(supportOption);
                        return supportOptions;
                    }

                    @Override
                    public void handleChatOption(SupportOption supportOption,
                                                 FragmentActivity context, Map<String, Object> solvvyState) {
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
        solvvySdkInstance.startSolvvy(this, "http://www.google.com");
    }

}
