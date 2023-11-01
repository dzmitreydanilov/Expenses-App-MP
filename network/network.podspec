Pod::Spec.new do |spec|
    spec.name                     = 'network'
    spec.version                  = '1.0'
    spec.homepage                 = 'Link to the Shared Module homepage'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'Modle which provides base abstract ApiService and Platform specific http clients'
    spec.vendored_frameworks      = 'build/cocoapods/framework/kmp_network_module.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '15'
                
                
    if !Dir.exist?('build/cocoapods/framework/kmp_network_module.framework') || Dir.empty?('build/cocoapods/framework/kmp_network_module.framework')
        raise "

        Kotlin framework 'kmp_network_module' doesn't exist yet, so a proper Xcode project can't be generated.
        'pod install' should be executed after running ':generateDummyFramework' Gradle task:

            ./gradlew :network:generateDummyFramework

        Alternatively, proper pod installation is performed during Gradle sync in the IDE (if Podfile location is set)"
    end
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':network',
        'PRODUCT_MODULE_NAME' => 'kmp_network_module',
    }
                
    spec.script_phases = [
        {
            :name => 'Build network',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end