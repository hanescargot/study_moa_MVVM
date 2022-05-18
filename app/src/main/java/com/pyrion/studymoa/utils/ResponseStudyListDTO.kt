package com.pyrion.studymoa.utils

class ResponseStudyListDTO {
    var studies : ArrayList<StudyDTO> = ArrayList()

    fun add(study : StudyDTO){
        studies.add(study)
    }

}