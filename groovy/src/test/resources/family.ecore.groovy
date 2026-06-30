ePackage {

    name 'familymodel'
    nsURI 'https://family.models.nasdanika.org'
    nsPrefix 'org.nasdanika.models.family'
    
    eAnnotation {

        source "http://www.eclipse.org/emf/2002/GenModel"

        details {

            key "documentation"
            value '''
            Family model is used to demonstrate different Nasdanika technologies such as:

            * Generating of metamodel (Ecore) documentation like this one
            * Loading of models from MS Excel and Drawio diagrams
            * Generation of HTML sites from loaded models

            The model was created to mimic the model from [Eclipse Sirius Basic Family](https://wiki.eclipse.org/Sirius/Tutorials/BasicFamily) tutorial.
            '''.stripIndent().trim()

        }

    }

    def namedElement = eClass {

        name 'NamedElement'

        eAttribute {

            name 'name'
            eType 'EString'
            lowerBound 1
            upperBound 1

            eAnnotation {

                source "http://www.eclipse.org/emf/2002/GenModel"

                details {

                    key "documentation"
                    value 'Named element name'

                }

            }

        }

    }

    eClass {

        name 'Family'
        eSuperTypes namedElement

        eAnnotation {

            source "http://www.eclipse.org/emf/2002/GenModel"

            details {

                key "documentation"
                value '''
                Family (from Latin: familia) is a group of people related either by consanguinity (by recognized birth) or affinity (by marriage or other relationship).
                It forms the basis for social order.
                The purpose of the family is to maintain the well-being of its members and of society.
                Ideally, families offer predictability, structure, and safety as members mature and learn to participate in the community.
                Historically, most human societies use family as the primary locus of attachment, nurturance, and socialization.

                Anthropologists classify most family organizations as matrifocal (a mother and her children), patrifocal (a father and his children), conjugal (a married couple with children, also called the nuclear family), avuncular (a man, his sister, and her children), or extended (in addition to parents and children, may include grandparents, aunts, uncles, or cousins).

                The field of genealogy aims to trace family lineages through history.
                The family is also an important economic unit studied in family economics.
                The word “families” can be used metaphorically to create more inclusive categories such as community, nationhood, and global village.
                '''.stripIndent().trim()

            }

        }

        eReference {

            name 'members'
            eType "/eClassifiers[name='Person']"
            lowerBound 2
            upperBound -1

            eAnnotation {

                source "http://www.eclipse.org/emf/2002/GenModel"

                details {

                    key "documentation"
                    value 'Family members'

                }

            }

        }

    }

    def person = eClass {

        name 'Person'
        eSuperTypes namedElement
        eSuperTypes "/eClassifiers[name='NamedElement']"

        eAnnotation {

            source "http://www.eclipse.org/emf/2002/GenModel"

            details {

                key "documentation"
                value '''
                A person (pl.: people or persons, depending on context) is a being who has certain capacities or attributes such as reason, morality, consciousness or self-consciousness, and being a part of a culturally established form of social relations such as kinship, ownership of property, or legal responsibility.
                The defining features of personhood and, consequently, what makes a person count as a person, differ widely among cultures and contexts.

                In addition to the question of personhood, of what makes a being count as a person to begin with, there are further questions about personal identity and self: both about what makes any particular person that particular person instead of another, and about what makes a person at one time the same person as they were or will be at another time despite any intervening changes.

                The plural form “people” is often used to refer to an entire nation or ethnic group (as in “a people”), and this was the original meaning of the word; it subsequently acquired its use as a plural form of person. The plural form “persons” is often used in philosophical and legal writing.
                '''.stripIndent().trim()

            }

        }

        eAttribute {

            name 'birthdate'
            eType 'EDate'
            lowerBound 0
            upperBound 1

            eAnnotation {

                source "http://www.eclipse.org/emf/2002/GenModel"

                details {

                    key "documentation"
                    value 'Person birthdate'

                }

            }

        }

        eReference {

            name 'parents'
            eType "/eClassifiers[name='Person']"
            lowerBound 0
            upperBound 2
            eOpposite "/eClassifiers[name='Person']/eStructuralFeatures[name='children']"

            eAnnotation {

                source "http://www.eclipse.org/emf/2002/GenModel"

                details {

                    key "documentation"
                    value 'Person parents'

                }

            }

        }

        eReference {

            name 'children'
            eType "/eClassifiers[name='Person']"
            lowerBound 0
            upperBound -1
            eOpposite "/eClassifiers[name='Person']/eStructuralFeatures[name='parents']"

            eAnnotation {

                source "http://www.eclipse.org/emf/2002/GenModel"

                details {

                    key "documentation"
                    value 'Person children'

                }

            }

        }

        eReference {

            name 'father'
            eType "/eClassifiers[name='Man']"
            lowerBound 0
            upperBound 1
            derived true

            eAnnotation {

                source "http://www.eclipse.org/emf/2002/GenModel"

                details {

                    key "documentation"
                    value 'Person father'

                }

            }
        }

        eReference {

            name 'mother'
            eType "/eClassifiers[name='Woman']"
            lowerBound 0
            upperBound 1
            derived true

            eAnnotation {

                source "http://www.eclipse.org/emf/2002/GenModel"

                details {

                    key "documentation"
                    value 'Person mother'

                }

            }
        }
    }

    eClass {

        name 'Man'
        eSuperTypes person

        eAnnotation {

            source "http://www.eclipse.org/emf/2002/GenModel"

            details {

                key "documentation"
                value '''
                A man is an adult male human. Prior to adulthood, a male human is referred to as a boy (a male child or adolescent).

                ### Etymology

                The English term “man” is derived from the Proto-Indo-European root *man- (see Sanskrit/Avestan manu-, Slavic mǫž “man, male”).
                More directly, the word derives from Old English mann.
                The Old English form primarily meant “person” or “human being” and referred to men, women, and children alike.
                The Old English word for “man” as distinct from “woman” or “child” was wer.
                Mann only came to mean “man” in Middle English, replacing wer, which survives today only in the compounds “werewolf” (from Old English werwulf, literally “man-wolf”), and “wergild”, literally “man-payment”.
                '''.stripIndent().trim()

            }

        }
    }

    eClass {

        name 'Woman'
        eSuperTypes person

        eAnnotation {

            source "http://www.eclipse.org/emf/2002/GenModel"

            details {

                key "documentation"
                value '''
                A woman is an adult female human. Prior to adulthood, one is referred to as a girl (a female child or adolescent).

                ### Etymology

                The spelling of “woman” in English has progressed over the past millennium from wīfmann to wīmmann to wumman, and finally, the modern spelling woman. 
                In Old English, wīfmann meant ‘woman’ (literally ‘woman-person’), whereas wermann meant ‘man’. 
                Mann had a gender-neutral meaning of ‘human’, corresponding to Modern English ‘person’ or ‘someone’; however, subsequent to the Norman Conquest, man began to be used more in reference to ‘male human’, and by the late 13th century it had begun to eclipse usage of the older term wer.
                The medial labial consonants f and m in wīfmann coalesced into the modern form “woman”, while the initial element wīf, which had also meant ‘woman’, underwent semantic narrowing to the sense of a married woman (‘wife’).
                '''.stripIndent().trim()

            }

        }
    }

}


