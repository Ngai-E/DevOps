job('Rewarded Frontend Docker Push Job') {
    scm {
        git('https://github.com/Ngai-E/Rewarded-Task.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Ngai-e')
            node / gitConfigEmail('asobingai@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('ngaie/rewarded-front-end')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
